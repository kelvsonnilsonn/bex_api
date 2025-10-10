package com.ecommerce.bex.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public OrderItem(CartItem cartItem, Order order) {
        this.order = order;
        this.productId = cartItem.getProduct().getId();
        this.productName = cartItem.getProduct().getProductName();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = cartItem.getItemPrice();
        this.totalPrice = cartItem.getTotalPrice();
    }
}