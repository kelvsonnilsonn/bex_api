package com.ecommerce.bex.command;

import com.ecommerce.bex.model.valueobjects.validation.*;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record RegisterCommand(
        @NotNull(message = AppConstants.USERNAME_NOT_NULL_MESSAGE) @ValidUsername String username,
        @NotNull(message = AppConstants.PASSWORD_NOT_NULL_MESSAGE) @ValidPassword String password,
        @NotNull(message = AppConstants.EMAIL_NOT_NULL_MESSAGE) @ValidEmail String email,
        @NotNull(message = "O CPF é obrigatório") @ValidCPF String cpf,

        String street,
        String city,
        String neighborhood,
        String country,
        int number,
        @ValidZipcode String zipcode
) {
}
