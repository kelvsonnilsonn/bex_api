package com.ecommerce.bex.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() { super("O email inserido não segue o padrão de emails (seuemail@dominio.XXX, permitindo o uso de . e _)"); }
    public InvalidEmailException(String message) {
        super(message);
    }
}
