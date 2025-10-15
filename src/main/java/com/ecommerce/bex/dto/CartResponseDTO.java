package com.ecommerce.bex.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record CartResponseDTO(
        Long id,
        Long user,
        int items,
        BigDecimal total

) implements Serializable {
}