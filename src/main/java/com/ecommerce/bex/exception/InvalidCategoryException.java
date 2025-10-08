package com.ecommerce.bex.exception;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException() { super("A categoria escolhida não existe"); }
    public InvalidCategoryException(String message) {
        super(message);
    }
}
