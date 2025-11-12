package com.ecommerce.bex.model;

import com.ecommerce.bex.exception.product.ProductNotInCartException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void removeProduct(Long productId){
        CartItem itemToRemove = items.stream()
                .filter(i -> Objects.equals(i.getProduct().getId(), productId))
                .findFirst().orElseThrow(ProductNotInCartException::new);
        items.remove(itemToRemove);
    }

    public BigDecimal calculateTotal(){
        BigDecimal amount = BigDecimal.ZERO;
        for(CartItem item : items){
            amount = amount.add(item.getItemPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return amount;
    }

    public void clearCart(){
        this.items.clear();
    }
}
