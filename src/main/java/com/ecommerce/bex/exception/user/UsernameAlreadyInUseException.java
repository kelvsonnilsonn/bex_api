package com.ecommerce.bex.exception.user;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException() { super("O nome de usuário já está em uso"); }
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
