package com.ecommerce.bex.event.order;

import com.ecommerce.bex.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderStatusChangedEvent(
        Long orderId,
        OrderStatus oldStatus,
        OrderStatus newStatus,
        LocalDateTime createdAt
) {
    public OrderStatusChangedEvent(Long orderId, OrderStatus oldStatus, OrderStatus newStatus){
        this(orderId, oldStatus, newStatus, LocalDateTime.now());
    }
}
