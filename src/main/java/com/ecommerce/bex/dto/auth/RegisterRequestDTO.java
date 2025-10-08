package com.ecommerce.bex.dto.auth;

public record RegisterRequestDTO(
        String username,
        String password,
        String email,
        String cpf,

        String street,
        String city,
        String neighborhood,
        String country,
        int number,
        String zipcode
) {
}
