package com.ecommerce.bex.command.product;

import com.ecommerce.bex.validation.ValidUsername;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateProductNameCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long productId,
        @NotNull(message = "Você precisa inserir o novo nome") @ValidUsername String newName
) {
}
