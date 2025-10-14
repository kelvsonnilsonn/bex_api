package com.ecommerce.bex.command.product;

public record UpdateProductNameCommand(Long productId, String newName) {
}
