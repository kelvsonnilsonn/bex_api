package com.ecommerce.bex.command.product;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProductStockCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long productId,
        @NotNull(message = "Você precisa inserir o novo estoque do produto") @Positive(message = AppConstants.PRODUCT_STOCK_POSITIVE_MESSAGE)
        Integer newStock
) {
}
