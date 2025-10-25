package com.ecommerce.bex.command.cart;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddItemToCartCommand (
        @NotNull(message = AppConstants.PRODUCT_ID_NN_MESSAGE) Long productId,
        @NotNull(message = "A quantidade é obrigatória") @Positive(message = "Informe a quantidade de produtos. Deve ser maior que 0")
        Integer quantity
) {
}