package com.ecommerce.bex.command.user;

import com.ecommerce.bex.model.valueobjects.validation.ValidUsername;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateUsernameByIdCommand(
        @NotNull(message = AppConstants.USER_ID_NN_MESSAGE) Long id,
        @NotNull(message = AppConstants.USERNAME_NOT_NULL_MESSAGE) @ValidUsername String newName
) {
}
