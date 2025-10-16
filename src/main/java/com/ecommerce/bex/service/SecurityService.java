package com.ecommerce.bex.service;

import com.ecommerce.bex.command.LoginCommand;
import com.ecommerce.bex.command.RegisterCommand;
import com.ecommerce.bex.command.user.UpdateUserPasswordCommand;
import com.ecommerce.bex.dto.AuthResponseDTO;
import com.ecommerce.bex.exception.*;
import com.ecommerce.bex.mapper.UserMapper;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.model.valueobjects.UserInformation;
import com.ecommerce.bex.model.valueobjects.Username;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.repository.UserRepository;
import com.ecommerce.bex.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final AuthenticationInformation authenticationInformation;

    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginCommand dto){
        User user = userRepository.findByUsername(dto.username()).orElseThrow(UserNotFoundException::new);
        if(!(passwordEncoder.matches(dto.password(), user.getPassword()))){
            throw new FailedLoginAttemptException("Senha incorreta");
        }
        String token = tokenService.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername());
    }

    public AuthResponseDTO register(RegisterCommand dto){
        validateUserData(dto);
        UserInformation userInformation = userMapper.toInformation(dto, passwordEncoder);
        User userToSave = new User(userInformation);
        User savedUser = userRepository.save(userToSave);
        Cart cart = new Cart(savedUser);
        cartRepository.save(cart);
        String token = tokenService.generateToken(userToSave);
        return new AuthResponseDTO(token, userToSave.getUsername());
    }

    public void updateUserPassword(UpdateUserPasswordCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        user.changePassword(command.newPassword(), passwordEncoder);
        userRepository.save(user);
    }

    private void validateUserData(RegisterCommand dto){
        Optional<User> userByName = userRepository.findByUsername(dto.username());
        if(userByName.isPresent()){
            throw new UserAlreadyExistsException();
        }
        Optional<User> userByCpf = userRepository.findByCpf(dto.cpf());
        if(userByCpf.isPresent()){
            throw new CPFAlreadyInUseException();
        }
        Optional<User> userByEmail = userRepository.findByEmail(dto.email());
        if(userByEmail.isPresent()){
            throw new EmailAlreadyInUseException();
        }
    }
}
