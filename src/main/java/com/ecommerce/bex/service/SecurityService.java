package com.ecommerce.bex.service;

import com.ecommerce.bex.dto.auth.AuthResponseDTO;
import com.ecommerce.bex.dto.auth.LoginRequestDTO;
import com.ecommerce.bex.dto.auth.RegisterRequestDTO;
import com.ecommerce.bex.exception.FailedLoginAttemptException;
import com.ecommerce.bex.exception.UserAlreadyExistsException;
import com.ecommerce.bex.mapper.UserMapper;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.model.valueobjects.user.*;
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
public class SecurityService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginRequestDTO dto){
        User user = userRepository.findByUsername(dto.username()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(!(passwordEncoder.matches(dto.password(), user.getPassword()))){
            throw new FailedLoginAttemptException();
        }
        String token = tokenService.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername());
    }

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO dto){
        Optional<User> user = userRepository.findByUsername(dto.username());
        if(user.isPresent()){
            throw new UserAlreadyExistsException();
        }
        UserInformation userInformation = userMapper.toInformation(dto, passwordEncoder);
        User userToSave = new User(userInformation);
        User savedUser = userRepository.save(userToSave);
        Cart cart = new Cart(savedUser);
        cartRepository.save(cart);
        String token = tokenService.generateToken(userToSave);
        return new AuthResponseDTO(token, userToSave.getUsername());
    }
}
