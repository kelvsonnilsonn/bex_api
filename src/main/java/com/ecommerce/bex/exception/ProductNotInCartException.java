package com.ecommerce.bex.exception;

public class ProductNotInCartException extends RuntimeException {
    public ProductNotInCartException() { super("O produto não está no carrinho"); }
    public ProductNotInCartException(String message) {
        super(message);
    }
}
