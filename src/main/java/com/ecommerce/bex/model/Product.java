package com.ecommerce.bex.model;

import com.ecommerce.bex.enums.ProductCategory;
import com.ecommerce.bex.model.valueobjects.product.ProductInformation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProductInformation productInformation;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public Product(ProductInformation productInformation, ProductCategory category){
        this.productInformation = productInformation;
        this.category = category;
    }
}
