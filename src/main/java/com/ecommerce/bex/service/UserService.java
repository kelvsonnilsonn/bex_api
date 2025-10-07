package com.ecommerce.bex.service;

import com.ecommerce.bex.model.User;
import com.ecommerce.bex.model.valueobjects.user.UserInformation;
import com.ecommerce.bex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(UserInformation userInformation){
        User user = new User(userInformation);
        userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("achei n"));
    }
}
