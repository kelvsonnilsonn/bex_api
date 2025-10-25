package com.ecommerce.bex.command.user;

import com.ecommerce.bex.model.valueobjects.validation.ValidEmail;
import jakarta.validation.constraints.NotNull;

public record UpdateEmailCommand(
        @NotNull @ValidEmail String newEmail
) {
}
