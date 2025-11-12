package com.ecommerce.bex.exception.cart;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException() { super("O carrinho não foi encontrado"); }
    public CartNotFoundException(String message) {
        super(message);
    }
}
