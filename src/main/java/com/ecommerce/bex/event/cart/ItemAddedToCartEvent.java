package com.ecommerce.bex.event.cart;

import java.time.LocalDateTime;

public record ItemAddedToCartEvent(
        Long cartId,
        Long productId,
        Long userId,
        Integer quantity,
        LocalDateTime createdAt
) {
    public ItemAddedToCartEvent(Long cartId, Long productId, Long userId, Integer quantity){
        this(cartId, productId, userId, quantity, LocalDateTime.now());
    }
}
