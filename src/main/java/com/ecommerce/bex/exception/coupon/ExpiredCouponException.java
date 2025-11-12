package com.ecommerce.bex.exception.coupon;

public class ExpiredCouponException extends RuntimeException {
    public ExpiredCouponException() {super("O cupom expirou");}
    public ExpiredCouponException(String message) {
        super(message);
    }
}
