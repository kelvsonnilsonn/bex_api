package com.ecommerce.bex.model;

import com.ecommerce.bex.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

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

    public BigDecimal getPrice(){
        return productInformation.getPrice();
    }

    public Long getSellerId(){
        return productInformation.getSellerId();
    }

    public String getProductName(){
        return productInformation.getName();
    }

    public int getStock(){
        return productInformation.getStock();
    }

    public void changeProductName(String newName){
        productInformation.setName(newName);
    }

    public void changeProductPrice(BigDecimal newPrice){
        productInformation.setPrice(newPrice);
    }

    public void changeProductStock(int newStock){
        productInformation.setStock(newStock);
    }

    public void decreaseStock(int quantity){
        this.productInformation.setStock(productInformation.getStock() - quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productInformation, product.productInformation) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productInformation, category);
    }
}
