package com.ecommerce.bex.exception.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() { super("O pedido não foi encontrado"); }
    public OrderNotFoundException(String message) {
        super(message);
    }
}
