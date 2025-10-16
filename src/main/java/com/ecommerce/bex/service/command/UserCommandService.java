package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.user.UpdateEmailCommand;
import com.ecommerce.bex.command.user.UpdateUsernameByIdCommand;
import com.ecommerce.bex.command.user.UpdateUsernameCommand;
import com.ecommerce.bex.enums.UserRole;
import com.ecommerce.bex.exception.InvalidEmailException;
import com.ecommerce.bex.exception.SmallPrivilegesException;
import com.ecommerce.bex.exception.UserNotFoundException;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.UserRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final AuthenticationInformation authenticationInformation;

    public void update(UpdateUsernameCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        user.changeUsername(command.newName());
        userRepository.save(user);
    }

    public void update(UpdateUsernameByIdCommand command){
        User user = userRepository.findById(command.id()).orElseThrow(UserNotFoundException::new);
        if(user.getRole().equals(UserRole.ADMIN_ROLE)){
            throw new SmallPrivilegesException();
        }
        user.changeUsername(command.newName());
        userRepository.save(user);
    }

    public void update(UpdateEmailCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        if(!command.oldEmail().equals(user.getEmail())){
            throw new InvalidEmailException("Este não é o seu email");
        }
        user.changeEmail(command.newEmail());
        userRepository.save(user);
    }
}
