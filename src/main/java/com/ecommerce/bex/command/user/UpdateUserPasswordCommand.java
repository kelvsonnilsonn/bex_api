package com.ecommerce.bex.command.user;

import com.ecommerce.bex.model.valueobjects.validation.ValidPassword;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateUserPasswordCommand(
        @NotNull(message = AppConstants.PASSWORD_NOT_NULL_MESSAGE) @ValidPassword String newPassword
) {
}
