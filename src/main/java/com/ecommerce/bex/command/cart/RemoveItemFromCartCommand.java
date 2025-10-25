package com.ecommerce.bex.command.cart;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record RemoveItemFromCartCommand(
        @NotNull(message = AppConstants.PRODUCT_ID_NN_MESSAGE) Long productId
) {
}
