package com.ecommerce.bex.exception;

public class SmallPrivilegesException extends RuntimeException {
    public SmallPrivilegesException() {super("Te falta permissão");}
    public SmallPrivilegesException(String message) {
        super(message);
    }
}
