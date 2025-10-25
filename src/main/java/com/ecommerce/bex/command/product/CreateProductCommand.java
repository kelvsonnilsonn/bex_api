package com.ecommerce.bex.command.product;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotNull(message = "O nome do produto é obrigatório") @Size(min = 4, max = 60, message = "O nome deve ter entre 4 e 60 caracteres")
        String name,
        @NotNull(message = "A curta descrição do produto é obrigatória") @Size(min = 30, message = "Insira uma descrição curta")
        String description,
        String image,
        @NotNull(message = "O preço do produto é obrigatório") @Positive (message = AppConstants.PRODUCT_PRICE_POSITIVE_MESSAGE)
        BigDecimal price,
        @NotNull(message = AppConstants.PRODUCT_STOCK_POSITIVE_MESSAGE) @Positive (message = "A quantidade de itens no estoque deve ser maior que 0 para vender.")
        int stock,
        @NotNull(message = "O ID do vendendor é obrigatório") Long sellerId,
        @NotNull(message = "A categoria do produto é obrigatória") String category
) {
}
