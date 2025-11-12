package com.ecommerce.bex.command.product;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record DeleteProductCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long productId,
        String reason
) {
}
