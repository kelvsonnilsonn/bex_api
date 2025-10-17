package com.ecommerce.bex.exception;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException() { super("O nome de usuário já está em uso"); }
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
