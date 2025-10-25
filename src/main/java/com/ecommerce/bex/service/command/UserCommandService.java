package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.user.DeleteUserCommand;
import com.ecommerce.bex.command.user.UpdateEmailCommand;
import com.ecommerce.bex.command.user.UpdateUsernameByIdCommand;
import com.ecommerce.bex.command.user.UpdateUsernameCommand;
import com.ecommerce.bex.enums.UserRole;
import com.ecommerce.bex.exception.*;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.UserRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final AuthenticationInformation authenticationInformation;

    @CacheEvict(value="users", key="#command.userId")
    public void delete(DeleteUserCommand command){
        User user = userRepository.findById(command.userId()).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @CacheEvict(value="users", key="#user.id")
    public void update(UpdateUsernameCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        validateUsername(command.newName(), user.getId());
        user.changeUsername(command.newName());
        userRepository.save(user);
    }

    @CacheEvict(value="users", key="#user.id")
    public void update(UpdateUsernameByIdCommand command){
        User user = userRepository.findById(command.id()).orElseThrow(UserNotFoundException::new);
        if(user.getRole().equals(UserRole.ADMIN_ROLE)){
            throw new SmallPrivilegesException();
        }
        validateUsername(command.newName(), user.getId());
        user.changeUsername(command.newName());
        userRepository.save(user);
    }

    @CacheEvict(value="users", key="#user.id")
    public void update(UpdateEmailCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        validateEmail(command.newEmail(), user.getId());
        user.changeEmail(command.newEmail());
        userRepository.save(user);
    }

    private void validateUsername(String username, Long userId){
        userRepository.findByUsername(username).ifPresent(userInDb -> {
            if(!userInDb.getId().equals(userId)){
                throw new UsernameAlreadyInUseException();
            }
        });
    }

    private void validateEmail(String email, Long userId){
        userRepository.findByEmail(email).ifPresent(userInDb -> {
            if(!userInDb.getId().equals(userId)){
                throw new EmailAlreadyInUseException();
            }
        });
    }
}
