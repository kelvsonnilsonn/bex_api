package com.ecommerce.bex.exception;

public class NotSellerException extends RuntimeException {
    public NotSellerException() { super("Apenas vendedores podem adicionar um produto"); }
    public NotSellerException(String message) {
        super(message);
    }
}
