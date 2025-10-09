package com.ecommerce.bex.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Setter
    private int quantity;

    private BigDecimal itemPrice = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItem(Product product, int quantity, Cart cart){
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.itemPrice = this.itemPrice.add(product.getPrice());
    }

    public Long getCartUserId(){
        return cart.getUserId();
    }

    public String getProductName(){
        return product.getProductName();
    }

    public Long getProductId(){
        return product.getId();
    }

    public Long getSellerId(){
        return product.getSellerId();
    }

    public BigDecimal getTotalPrice(){
        return itemPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
