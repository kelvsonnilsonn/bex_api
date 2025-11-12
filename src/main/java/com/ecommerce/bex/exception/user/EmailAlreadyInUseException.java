package com.ecommerce.bex.exception.user;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException() { super("O Email já está em uso"); }
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
