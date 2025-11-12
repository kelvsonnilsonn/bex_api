package com.ecommerce.bex.command.user;

import com.ecommerce.bex.validation.ValidUsername;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateUsernameByIdCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long id,
        @NotNull(message = AppConstants.USERNAME_NOT_NULL_MESSAGE) @ValidUsername String newName
) {
}
