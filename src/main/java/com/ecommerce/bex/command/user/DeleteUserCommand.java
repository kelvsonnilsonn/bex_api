package com.ecommerce.bex.command.user;

import jakarta.validation.constraints.NotNull;

public record DeleteUserCommand(
        @NotNull Long userId,
        String reason
) {
}
