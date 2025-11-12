package com.ecommerce.bex.command.user;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record DeleteUserCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long userId,
        String reason
) {
}
