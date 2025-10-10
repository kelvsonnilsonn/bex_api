package com.ecommerce.bex.mapper;

import com.ecommerce.bex.dto.OrderItemResponseDTO;
import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.enums.OrderStatus;
import com.ecommerce.bex.model.Order;
import com.ecommerce.bex.model.OrderItem;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    default OrderResponseDTO toResponse(Order order){
        Long id = order.getId();
        Long userId = order.getUserId();
        List<OrderItemResponseDTO> items = order.getItems().stream().map(this::toItemResponse).toList();
        String street = order.getStreet();
        String city = order.getCity();
        String neighborhood = order.getNeighborhood();
        String country = order.getCountry();
        int number = order.getNumber();
        String zipcode = order.getZipcode();
        BigDecimal totalPrice = order.getTotalPrice();
        OrderStatus status = order.getStatus();
        return new OrderResponseDTO(id, userId, items, street, city, neighborhood, country, number, zipcode, totalPrice, status);
    }

    OrderItemResponseDTO toItemResponse(OrderItem item);

}
