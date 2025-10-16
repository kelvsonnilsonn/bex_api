package com.ecommerce.bex.service.query;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.UserResponseDTO;
import com.ecommerce.bex.mapper.UserMapper;
import com.ecommerce.bex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public PageResponseDTO<UserResponseDTO> findAll(Pageable pageable){
        Page<UserResponseDTO> users = userRepository.findAll(pageable).map(userMapper::toResponse);
        return PageResponseDTO.fromPage(users);
    }

}
