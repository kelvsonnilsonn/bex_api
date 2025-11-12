package com.ecommerce.bex.exception.coupon;

public class CouponLimitException extends RuntimeException {
    public CouponLimitException() { super("O limite do cupom foi atingido.");}
    public CouponLimitException(String message) {
        super(message);
    }
}
