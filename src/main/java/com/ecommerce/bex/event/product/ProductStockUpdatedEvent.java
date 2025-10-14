package com.ecommerce.bex.event.product;

import java.time.LocalDateTime;

public record ProductStockUpdatedEvent(
        Long productId,
        Long userId,
        Integer oldStock,
        Integer newStock,
        LocalDateTime createdAt
) {
    public ProductStockUpdatedEvent(Long productId, Long userId, Integer oldStock, Integer newStock){
        this(productId, userId, oldStock, newStock, LocalDateTime.now());
    }
}
