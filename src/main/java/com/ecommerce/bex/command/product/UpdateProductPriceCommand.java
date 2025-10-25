package com.ecommerce.bex.command.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductPriceCommand(
        @NotNull Long productId,
        @NotNull @Positive(message = "O novo preço do produto deve ser maior que 0!") BigDecimal newPrice
) {
}
