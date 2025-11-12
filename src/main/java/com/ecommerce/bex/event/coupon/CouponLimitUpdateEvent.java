package com.ecommerce.bex.event.coupon;

import java.time.LocalDateTime;

public record CouponLimitUpdateEvent(
        Long couponId,
        Long adminId,
        int olderLimit,
        int newLimit,
        LocalDateTime createdAt
) {
    public CouponLimitUpdateEvent(Long couponId, Long adminId, int olderLimit, int newLimit){
        this(couponId, adminId, olderLimit, newLimit, LocalDateTime.now());
    }
}
