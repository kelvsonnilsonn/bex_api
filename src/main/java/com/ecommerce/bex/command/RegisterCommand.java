package com.ecommerce.bex.command;

import com.ecommerce.bex.util.AppConstants;
import com.ecommerce.bex.validation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegisterCommand(
        @NotNull(message = AppConstants.USERNAME_NOT_NULL_MESSAGE) @ValidUsername String username,
        @NotNull(message = AppConstants.PASSWORD_NOT_NULL_MESSAGE) @ValidPassword String password,
        @NotNull(message = AppConstants.EMAIL_NOT_NULL_MESSAGE) @ValidEmail String email,
        @NotNull(message = "O CPF é obrigatório") @ValidCPF String cpf,

        String street,
        String city,
        String neighborhood,
        String country,
        @Positive(message = AppConstants.POSITIVE_ADDRESS_NUMBER_MESSAGE) int number,
        @ValidZipcode String zipcode
) {
}
