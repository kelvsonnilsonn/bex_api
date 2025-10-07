package com.ecommerce.bex.exception;

public class InvalidProductStatusException extends RuntimeException {
    public InvalidProductStatusException() { super("O produto já foi entregue"); }
    public InvalidProductStatusException(String message) {
        super(message);
    }
}
