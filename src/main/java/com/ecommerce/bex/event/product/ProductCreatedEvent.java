package com.ecommerce.bex.event.product;

import com.ecommerce.bex.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductCreatedEvent(
        Long productId,
        Long sellerId,
        String productName,
        BigDecimal price,
        Integer stock,
        LocalDateTime createdAt
) {
    public ProductCreatedEvent(Product product){
        this(product.getId(), product.getSellerId(), product.getProductName(), product.getPrice(), product.getStock(), LocalDateTime.now());
    }
}
