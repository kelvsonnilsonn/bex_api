package com.ecommerce.bex.command;

import com.ecommerce.bex.model.valueobjects.validation.*;
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
        @ValidZipcode String zipcode
) {
}
