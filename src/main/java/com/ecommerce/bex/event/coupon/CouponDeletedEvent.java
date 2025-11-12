package com.ecommerce.bex.event.coupon;

import java.time.LocalDateTime;

public record CouponDeletedEvent(
        Long couponId,
        Long adminId,
        String reason,
        LocalDateTime createdAt
) {
    public CouponDeletedEvent(Long couponId, Long adminId, String reason){
        this(couponId, adminId, reason, LocalDateTime.now());
    }
}
