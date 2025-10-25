package com.ecommerce.bex.command.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductCommand(
        @NotNull @Size(min = 4, max = 60, message = "O nome deve ter entre 4 e 60 caracteres") String name,
        @NotNull @Size(max = 250, message = "Você deve adicionar uma curta descrição sobre o produto") String description,
        String image,
        @NotNull @Positive (message = "O preço do produto deve ser maior que 0!") BigDecimal price,
        @NotNull @Positive (message = "A quantidade de itens no estoque deve ser maior que 0 para vender.") int stock,
        @NotNull Long sellerId,
        @NotNull String category
) {
}
