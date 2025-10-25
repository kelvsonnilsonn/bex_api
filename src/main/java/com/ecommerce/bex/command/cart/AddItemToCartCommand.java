package com.ecommerce.bex.command.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddItemToCartCommand (
        @NotNull Long productId,
        @NotNull @Positive Integer quantity
) {
}