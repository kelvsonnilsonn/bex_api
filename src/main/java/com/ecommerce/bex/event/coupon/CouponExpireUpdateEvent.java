package com.ecommerce.bex.event.coupon;

import java.time.LocalDateTime;

public record CouponExpireUpdateEvent(
        Long couponId,
        Long adminId,
        LocalDateTime olderExpireDate,
        LocalDateTime newExpireDate,
        LocalDateTime createdAt
) {
    public CouponExpireUpdateEvent(Long couponId, Long adminId, LocalDateTime olderExpireDate, LocalDateTime newExpireDate){
        this(couponId, adminId, olderExpireDate, newExpireDate, LocalDateTime.now());
    }
}
