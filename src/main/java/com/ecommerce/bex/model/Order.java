package com.ecommerce.bex.model;

import com.ecommerce.bex.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private Long userId;

    private String zipcode;
    private String street;
    private String city;
    private String neighborhood;
    private String country;
    private int number;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Order(Long userId, List<CartItem> items){
        this.userId = userId;
        this.status = OrderStatus.PENDING;
        for (CartItem cartItem : items) {
            this.items.add(new OrderItem(cartItem, this));
        }
        calculateTotal();
    }

    public void nextStatus(){
        this.status.nextStatus(this);
    }

    private void calculateTotal(){
        this.totalPrice = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
