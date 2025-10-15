package com.ecommerce.bex.dto;

import com.ecommerce.bex.enums.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Long userId,
        List<OrderItemResponseDTO> items,
        String street,
        String city,
        String neighborhood,
        String country,
        int number,
        String zipcode,
        BigDecimal totalPrice,
        OrderStatus status
) implements Serializable {
}
