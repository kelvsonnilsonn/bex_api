package com.ecommerce.bex.mapper;

import com.ecommerce.bex.dto.CartResponseDTO;
import com.ecommerce.bex.dto.ItemResponseDTO;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "items", expression = "java(cart.getItemsSize())")
    @Mapping(target = "user", expression = "java(cart.getUserId())")
    @Mapping(target =  "total", expression = "java(cart.calculateTotal())")
    CartResponseDTO toResponse(Cart cart);

    @Mapping(target = "user", expression = "java(item.getCartUserId())")
    @Mapping(target = "productName", expression = "java(item.getProductName())")
    @Mapping(target = "sellerId", expression = "java(item.getSellerId())")
    @Mapping(target = "productId", expression = "java(item.getProductId())")
    @Mapping(target = "total", expression = "java(item.getTotalPrice())")
    ItemResponseDTO toItemResponse(CartItem item);
}
