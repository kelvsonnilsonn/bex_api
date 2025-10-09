package com.ecommerce.bex.repository;

import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user.id = :id")
    Optional<Cart> findByUserId(Long id);

    @Query("SELECT i FROM Cart c, CartItem i WHERE i.cart.id = :id")
    Page<CartItem> findProductInCart(Pageable pageable, Long id);
}

