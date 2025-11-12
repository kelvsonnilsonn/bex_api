package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.cart.AddItemToCartCommand;
import com.ecommerce.bex.command.cart.RemoveItemFromCartCommand;
import com.ecommerce.bex.event.cart.CartClearedEvent;
import com.ecommerce.bex.event.cart.ItemAddedToCartEvent;
import com.ecommerce.bex.event.cart.ItemRemovedFromCartEvent;
import com.ecommerce.bex.exception.product.ProductNotFoundException;
import com.ecommerce.bex.exception.user.UserNotFoundException;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.repository.ProductRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import com.ecommerce.bex.service.EventStoreService;
import com.ecommerce.bex.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartCommandService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthenticationInformation authenticationInformation;
    private final EventStoreService eventStoreService;

    @CacheEvict(value = {"my-cart", "cart-products"}, allEntries = true)
    public void addItemToCart(AddItemToCartCommand command){
        User user = getAuthenticatedUser();
        Cart cart = getUserCart(user.getId());
        Product product = productRepository.findById(command.productId()).orElseThrow(ProductNotFoundException::new);
        cart.addProduct(product, command.quantity());
        cartRepository.save(cart);
        ItemAddedToCartEvent event = new ItemAddedToCartEvent(cart.getId(), product.getId(), user.getId(), command.quantity());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_CART_TYPE, cart.getId(), event);
    }

    @CacheEvict(value = {"my-cart", "cart-products"}, allEntries = true)
    public void removeItemFromCart(RemoveItemFromCartCommand command){
        User user = getAuthenticatedUser();
        Cart cart = getUserCart(user.getId());
        cart.removeProduct(command.productId());
        cartRepository.save(cart);
        ItemRemovedFromCartEvent event = new ItemRemovedFromCartEvent(cart.getId(), command.productId(), user.getId());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_CART_TYPE, cart.getId(), event);
    }

    @CacheEvict(value = {"my-cart", "cart-products"}, allEntries = true)
    public void clearCart(){
        User user = getAuthenticatedUser();
        Cart cart = getUserCart(user.getId());
        cart.clearCart();
        cartRepository.save(cart);
        CartClearedEvent event = new CartClearedEvent(cart.getId(), user.getId(), "");
        eventStoreService.saveEvent(AppConstants.AGGREGATE_CART_TYPE, cart.getId(), event);
    }

    private Cart getUserCart(Long userId){
        return cartRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private User getAuthenticatedUser(){
        return authenticationInformation.getAuthenticatedUser();
    }
}
