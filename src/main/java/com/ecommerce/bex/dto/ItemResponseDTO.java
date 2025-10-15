package com.ecommerce.bex.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ItemResponseDTO(
        Long user,
        String productName,
        int quantity,
        BigDecimal total,
        Long sellerId,
        Long productId
) implements Serializable {
}
