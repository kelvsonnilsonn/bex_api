package com.ecommerce.bex.command.product;

import jakarta.validation.constraints.NotNull;

public record DeleteProductCommand(
        @NotNull Long productId,
        String reason
) {
}
