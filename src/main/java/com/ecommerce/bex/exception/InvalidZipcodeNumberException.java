package com.ecommerce.bex.exception;

public class InvalidZipcodeNumberException extends RuntimeException {
    public InvalidZipcodeNumberException() { super("O CEP inserido não segue um padrão válido (XXXX-XXX ou XXXXXXX)"); }
    public InvalidZipcodeNumberException(String message) {
        super(message);
    }
}
