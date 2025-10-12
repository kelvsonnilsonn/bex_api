package com.ecommerce.bex.exception;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {super("O seu carrinho está vazio");}
    public EmptyCartException(String message) {
        super(message);
    }
}
