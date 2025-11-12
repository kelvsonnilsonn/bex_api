package com.ecommerce.bex.exception.coupon;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException() {super("Cupom não encontrado");}
    public CouponNotFoundException(String message) {
        super(message);
    }
}
