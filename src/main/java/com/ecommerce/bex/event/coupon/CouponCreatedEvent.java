package com.ecommerce.bex.event.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponCreatedEvent(
        Long couponId,
        Long adminId,
        String code,
        BigDecimal discountValue,
        Integer usageLimit,
        LocalDateTime expiresAt,
        LocalDateTime createdAt
) {
    public CouponCreatedEvent(Long couponId, Long adminId, String code, BigDecimal discountValue, int usageLimit, LocalDateTime expiresAt){
        this(couponId, adminId, code, discountValue, usageLimit, expiresAt, LocalDateTime.now());
    }
}
