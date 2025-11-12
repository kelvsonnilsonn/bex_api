package com.ecommerce.bex.service.query;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.UserResponseDTO;
import com.ecommerce.bex.exception.user.UserNotFoundException;
import com.ecommerce.bex.mapper.UserMapper;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public PageResponseDTO<UserResponseDTO> findAll(Pageable pageable){
        Page<UserResponseDTO> users = userRepository.findAll(pageable).map(userMapper::toResponse);
        return PageResponseDTO.fromPage(users);
    }

    @Cacheable("users-by-username")
    public UserResponseDTO findByName(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return userMapper.toResponse(user);
    }

    @Cacheable("users-by-email")
    public UserResponseDTO findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userMapper.toResponse(user);
    }

}
