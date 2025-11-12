package com.ecommerce.bex.command.user;

import com.ecommerce.bex.validation.ValidEmail;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateEmailCommand(
        @NotNull(message = AppConstants.EMAIL_NOT_NULL_MESSAGE) @ValidEmail String newEmail
) {
}
