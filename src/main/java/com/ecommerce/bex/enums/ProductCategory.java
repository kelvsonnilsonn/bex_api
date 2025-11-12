package com.ecommerce.bex.enums;

import com.ecommerce.bex.exception.product.InvalidCategoryException;

public enum ProductCategory {
    ELETRONICOS,
    ELETRODOMESTICOS,
    INFORMATICA,
    CELULARES,
    MODA,
    BELEZA,
    CASA,
    ESPORTE,
    LIVROS,
    BRINQUEDOS,
    AUTOMOTIVO,
    FERRAMENTAS,
    SAUDE,
    PET,
    SUPERMERCADO,
    JOGOS,
    MUSICA,
    FILMES;

    public static ProductCategory fromString(String category) {
        try {
            return ProductCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException();
        }
    }
}