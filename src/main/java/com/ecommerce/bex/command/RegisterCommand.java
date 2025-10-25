package com.ecommerce.bex.command;

import com.ecommerce.bex.model.valueobjects.validation.ValidCPF;
import com.ecommerce.bex.model.valueobjects.validation.ValidEmail;
import com.ecommerce.bex.model.valueobjects.validation.ValidPassword;
import com.ecommerce.bex.model.valueobjects.validation.ValidUsername;
import jakarta.validation.constraints.NotNull;

public record RegisterCommand(
        @NotNull @ValidUsername String username,
        @NotNull @ValidPassword String password,
        @NotNull @ValidEmail String email,
        @NotNull @ValidCPF String cpf,

        String street,
        String city,
        String neighborhood,
        String country,
        int number,
        String zipcode
) {
}
