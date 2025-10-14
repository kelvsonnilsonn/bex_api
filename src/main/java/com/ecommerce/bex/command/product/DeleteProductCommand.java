package com.ecommerce.bex.command.product;

public record DeleteProductCommand(Long productId, String reason) {
}
