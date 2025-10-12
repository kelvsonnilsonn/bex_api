package com.ecommerce.bex.command.product;

public record DecreaseStockCommand(Long id, Integer quantity) {
}
