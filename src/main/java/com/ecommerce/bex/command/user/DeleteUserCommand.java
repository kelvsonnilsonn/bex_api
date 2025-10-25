package com.ecommerce.bex.command.user;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record DeleteUserCommand(
        @NotNull(message = AppConstants.USER_ID_NN_MESSAGE) Long userId,
        String reason
) {
}
