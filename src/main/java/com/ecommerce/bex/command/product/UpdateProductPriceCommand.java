package com.ecommerce.bex.command.product;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductPriceCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long productId,
        @NotNull(message = "Você precisa inserir o novo preço") @Positive (message = AppConstants.PRODUCT_PRICE_POSITIVE_MESSAGE)
        BigDecimal newPrice
) {
}
