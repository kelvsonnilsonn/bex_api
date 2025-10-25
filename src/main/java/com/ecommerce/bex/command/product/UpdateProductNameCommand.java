package com.ecommerce.bex.command.product;

import com.ecommerce.bex.model.valueobjects.validation.ValidUsername;
import jakarta.validation.constraints.NotNull;

public record UpdateProductNameCommand(
        @NotNull Long productId,
        @NotNull @ValidUsername String newName
) {
}
