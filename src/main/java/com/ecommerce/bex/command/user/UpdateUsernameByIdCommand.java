package com.ecommerce.bex.command.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUsernameByIdCommand(
        @NotNull Long id,
        String newName
) {
}
