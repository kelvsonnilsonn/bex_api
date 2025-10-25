package com.ecommerce.bex.command.product;

import com.ecommerce.bex.model.valueobjects.validation.ValidUsername;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateProductNameCommand(
        @NotNull(message = AppConstants.PRODUCT_ID_NN_MESSAGE) Long productId,
        @NotNull(message = "Você precisa inserir o novo nome") @ValidUsername String newName
) {
}
