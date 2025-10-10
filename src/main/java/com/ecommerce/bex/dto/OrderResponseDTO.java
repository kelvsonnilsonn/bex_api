package com.ecommerce.bex.dto;

import com.ecommerce.bex.dto.cart.CartResponseDTO;
import com.ecommerce.bex.enums.OrderStatus;

import java.util.List;

public record OrderResponseDTO(
        Long id,
        Long userId,
        List<CartResponseDTO> cart,
        String street,
        String city,
        String neighborhood,
        String country,
        int number,
        String zipcode,
        OrderStatus status
) {
}
