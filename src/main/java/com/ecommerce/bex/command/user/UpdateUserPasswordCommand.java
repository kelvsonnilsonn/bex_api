package com.ecommerce.bex.command.user;

public record UpdateUserPasswordCommand(
        String newPassword
) {
}
