package com.ecommerce.bex.command.product;

import java.math.BigDecimal;

public record CreateProductCommand(
        String name,
        String description,
        String image,
        BigDecimal price,
        int stock,
        Long sellerId,
        String category
) {
}
