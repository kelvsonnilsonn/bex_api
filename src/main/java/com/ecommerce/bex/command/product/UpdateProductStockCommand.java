package com.ecommerce.bex.command.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProductStockCommand(
        @NotNull Long productId,
        @NotNull @Positive Integer newStock
) {
}
