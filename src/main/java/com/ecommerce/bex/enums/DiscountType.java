package com.ecommerce.bex.enums;

import com.ecommerce.bex.exception.product.InvalidCategoryException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum DiscountType {
    PERCENTAGE {
        @Override
        public BigDecimal applyCoupon(BigDecimal total, BigDecimal discountValue){
            BigDecimal raw_percentage = discountValue.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
            BigDecimal percentageFactor = BigDecimal.ONE.subtract(raw_percentage);
            return total.multiply(percentageFactor).setScale(2, RoundingMode.HALF_UP);
        }
    },

    FIXED_AMOUNT {
        @Override
        public BigDecimal applyCoupon(BigDecimal total, BigDecimal discountValue){
            BigDecimal totalWithDiscount = total.subtract(discountValue);
            return totalWithDiscount.compareTo(BigDecimal.ZERO) > 0 ? totalWithDiscount : BigDecimal.ZERO;
        }
    };

    public abstract BigDecimal applyCoupon(BigDecimal total, BigDecimal discountValue);

    public static DiscountType fromString(String category) {
        try {
            return DiscountType.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException();
        }
    }
}
