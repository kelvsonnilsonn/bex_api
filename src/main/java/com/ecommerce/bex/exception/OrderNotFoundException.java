package com.ecommerce.bex.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() { super("O pedido não foi encontrado"); }
    public OrderNotFoundException(String message) {
        super(message);
    }
}
