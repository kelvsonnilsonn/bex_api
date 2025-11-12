package com.ecommerce.bex.command.product;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DecreaseStockCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long productId,
        @NotNull @Positive(message = "A quantidade de itens a serem removidos do estoque deve ser maior que 0!") Integer quantity
) {
}
