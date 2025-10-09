package com.ecommerce.bex.dto.cart;

import java.math.BigDecimal;

public record CartResponseDTO(
        Long id,
        Long user,
        int items,
        BigDecimal total

) {
}
