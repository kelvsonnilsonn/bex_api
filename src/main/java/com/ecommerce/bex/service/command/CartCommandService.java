package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.cart.AddItemToCartCommand;
import com.ecommerce.bex.exception.ProductNotFoundException;
import com.ecommerce.bex.exception.UserNotFoundException;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.repository.ProductRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartCommandService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthenticationInformation authenticationInformation;

    public void addItemToCart(AddItemToCartCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(command.productId()).orElseThrow(ProductNotFoundException::new);

        cart.addProduct(product, command.quantity());
        cartRepository.save(cart);
    }
}
