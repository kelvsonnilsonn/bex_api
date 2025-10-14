package com.ecommerce.bex.event.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderCreatedEvent(
        Long orderId,
        Long userId,
        List<OrderItem> items,
        BigDecimal total,
        LocalDateTime createdAt
) {
    public OrderCreatedEvent(Long orderId, Long userId, List<OrderItem> items, BigDecimal total){
        this(orderId, userId, items, total, LocalDateTime.now());
    }
}
