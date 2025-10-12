package com.ecommerce.bex.command;

public record RegisterCommand(
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
