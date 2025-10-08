package com.ecommerce.bex.dto.product;

import java.math.BigDecimal;

public record ProductCreateRequestDTO(
        String name,
        String description,
        String image,
        BigDecimal price,
        int stock,
        Long sellerId,
        String category
) {
}
