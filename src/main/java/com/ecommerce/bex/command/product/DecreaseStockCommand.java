package com.ecommerce.bex.command.product;

public record DecreaseStockCommand(Long productId, Integer quantity) {
}
