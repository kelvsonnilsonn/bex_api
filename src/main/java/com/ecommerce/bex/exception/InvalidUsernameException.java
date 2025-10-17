package com.ecommerce.bex.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() { super("O nome de usuário é inválido"); }
    public InvalidUsernameException(String message) {
        super(message);
    }
}
