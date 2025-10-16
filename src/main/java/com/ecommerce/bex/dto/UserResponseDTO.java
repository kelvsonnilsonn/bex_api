package com.ecommerce.bex.dto;

import com.ecommerce.bex.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String username,
        UserRole role,
        LocalDateTime createdAt
) {
}
