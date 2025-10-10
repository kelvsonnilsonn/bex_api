package com.ecommerce.bex.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long productId,
        String productName,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {
}
