package com.ecommerce.bex.command.user;

public record UpdateEmailCommand(
        String oldEmail,
        String newEmail
) {
}
