package com.ecommerce.bex.event.order;

import java.time.LocalDateTime;

public record OrderCancelledEvent(
        Long orderId,
        Long userId,
        String reason,
        LocalDateTime createdAt
) {
    public OrderCancelledEvent(Long orderId, Long userId, String reason){
        this(orderId, userId, reason, LocalDateTime.now());
    }
}
