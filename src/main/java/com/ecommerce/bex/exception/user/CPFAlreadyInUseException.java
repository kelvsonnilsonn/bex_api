package com.ecommerce.bex.exception.user;

public class CPFAlreadyInUseException extends RuntimeException {
    public CPFAlreadyInUseException() { super("O CPF já está em uso"); }
    public CPFAlreadyInUseException(String message) {
        super(message);
    }
}
