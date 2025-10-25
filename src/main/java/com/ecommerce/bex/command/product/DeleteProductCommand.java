package com.ecommerce.bex.command.product;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record DeleteProductCommand(
        @NotNull(message = AppConstants.PRODUCT_ID_NN_MESSAGE) Long productId,
        String reason
) {
}
