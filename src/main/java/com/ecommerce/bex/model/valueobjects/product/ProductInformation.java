package com.ecommerce.bex.model.valueobjects.product;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductInformation {
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private int stock;
    private Long sellerId;
}
