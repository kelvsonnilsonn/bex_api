package com.ecommerce.bex.event.order;

import java.math.BigDecimal;

public record OrderItem(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice
) {
}
