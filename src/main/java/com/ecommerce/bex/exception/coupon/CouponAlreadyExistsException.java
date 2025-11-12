package com.ecommerce.bex.exception.coupon;

public class CouponAlreadyExistsException extends RuntimeException {
    public CouponAlreadyExistsException() {super("O código de cupom já existe");}
    public CouponAlreadyExistsException(String message) {
        super(message);
    }
}
