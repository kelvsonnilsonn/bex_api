package com.ecommerce.bex.event.cart;

import java.time.LocalDateTime;

public record CartClearedEvent(
        Long cartId,
        Long userId,
        String reason,
        LocalDateTime createdAt
) {
    public CartClearedEvent(Long cartId, Long userId, String reason){
        this(cartId, userId, reason, LocalDateTime.now());
    }
}
