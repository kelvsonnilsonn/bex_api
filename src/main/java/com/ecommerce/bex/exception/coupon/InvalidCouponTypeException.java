package com.ecommerce.bex.exception.coupon;

public class InvalidCouponTypeException extends RuntimeException {
    public InvalidCouponTypeException() {super("O tipo de desconto é inválido");}
    public InvalidCouponTypeException(String message) {
        super(message);
    }
}
