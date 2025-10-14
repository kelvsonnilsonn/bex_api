package com.ecommerce.bex.event.cart;

import java.time.LocalDateTime;

public record ItemRemovedFromCartEvent(
        Long cartId,
        Long productId,
        Long userId,
        LocalDateTime createAt
) {
    public ItemRemovedFromCartEvent(Long cartId, Long productId, Long userId){
        this(cartId, productId, userId, LocalDateTime.now());
    }
}
