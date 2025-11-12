package com.ecommerce.bex.exception;

public class UnauthorizedCommandException extends RuntimeException {
    public UnauthorizedCommandException() {super("Você não tem autorização");}
    public UnauthorizedCommandException(String message) {
        super(message);
    }
}
