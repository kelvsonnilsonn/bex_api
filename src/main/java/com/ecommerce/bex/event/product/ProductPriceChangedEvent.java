package com.ecommerce.bex.event.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPriceChangedEvent(
        Long productId,
        Long userId,
        BigDecimal olderPrice,
        BigDecimal newPrice,
        LocalDateTime createdAt
) {
    public ProductPriceChangedEvent(Long productId, Long userId, BigDecimal olderPrice, BigDecimal newPrice){
        this(productId, userId, olderPrice, newPrice, LocalDateTime.now());
    }
}
