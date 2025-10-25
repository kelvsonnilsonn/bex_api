package com.ecommerce.bex.command.user;

import com.ecommerce.bex.model.valueobjects.validation.ValidUsername;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateUsernameCommand(
        @NotNull(message = AppConstants.USERNAME_NOT_NULL_MESSAGE) @ValidUsername String newName
) {
}
