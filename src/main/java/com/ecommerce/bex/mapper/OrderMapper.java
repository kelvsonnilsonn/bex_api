package com.ecommerce.bex.mapper;

import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO toResponse(Order order);
}
