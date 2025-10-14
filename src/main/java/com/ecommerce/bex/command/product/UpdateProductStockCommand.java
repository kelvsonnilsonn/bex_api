package com.ecommerce.bex.command.product;

public record UpdateProductStockCommand(Long productId, Integer newStock) {
}
