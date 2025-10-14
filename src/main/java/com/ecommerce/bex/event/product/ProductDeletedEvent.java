package com.ecommerce.bex.event.product;

import java.time.LocalDateTime;

public record ProductDeletedEvent(
        Long productId,
        Long userId,
        String productName,
        String reason,
        LocalDateTime createdAt
) {
    public ProductDeletedEvent(Long productId, Long userId, String productName, String reason){
        this(productId, userId, productName, reason, LocalDateTime.now());
    }
}
