package com.ecommerce.bex.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> items = new ArrayList<>();

    private BigDecimal total = BigDecimal.ZERO;

    public Cart(User user){
        this.user = user;
    }

    public Long getUserId(){
        return user.getId();
    }

    public int getItemsSize(){
        return items.size();
    }

    public void addProduct(Product product, int qtd){
        CartItem item = items.stream().filter(i -> i.getProduct().equals(product)).findFirst().orElse(null);
        if(item != null){
            item.setQuantity(item.getQuantity() + qtd);
        } else {
            this.items.add(new CartItem(product, qtd, this));
        }
        this.total = calculateTotal();
    }

    public BigDecimal calculateTotal(){
        BigDecimal amount = BigDecimal.ZERO;
        for(CartItem item : items){
            amount = amount.add(item.getItemPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return amount;
    }
}
