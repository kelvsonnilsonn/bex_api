package com.ecommerce.bex.exception;

public class ProductAlreadyReceivedException extends RuntimeException {
    public ProductAlreadyReceivedException() { super("O produto já foi entregue"); }
    public ProductAlreadyReceivedException(String message) {
        super(message);
    }
}
