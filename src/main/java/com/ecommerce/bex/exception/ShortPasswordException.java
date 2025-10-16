package com.ecommerce.bex.exception;

public class ShortPasswordException extends RuntimeException {
    public ShortPasswordException() { super("A senha inserida é curta demais."); }
    public ShortPasswordException(String message) {
        super(message);
    }
}
