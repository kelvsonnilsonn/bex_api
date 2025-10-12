package com.ecommerce.bex.mapper;

import com.ecommerce.bex.command.product.CreateProductCommand;
import com.ecommerce.bex.dto.ProductResponseDTO;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.ProductInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "name", source = "productInformation.name")
    @Mapping(target = "description", source = "productInformation.description")
    @Mapping(target = "price", source = "productInformation.price")
    @Mapping(target = "image", source = "productInformation.image")
    @Mapping(target = "stock", source = "productInformation.stock")
    @Mapping(target = "sellerId", source = "productInformation.sellerId")
    ProductResponseDTO toResponse(Product product);

    ProductInformation toInformation(CreateProductCommand command);
}
