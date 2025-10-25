package com.ecommerce.bex.command.cart;

import jakarta.validation.constraints.NotNull;

public record RemoveItemFromCartCommand(
        @NotNull Long productId
) {
}
