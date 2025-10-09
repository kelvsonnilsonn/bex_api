package com.ecommerce.bex.service;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.cart.CartAddRequestDTO;
import com.ecommerce.bex.dto.cart.CartResponseDTO;
import com.ecommerce.bex.dto.cart.ItemResponseDTO;
import com.ecommerce.bex.exception.ProductNotFoundException;
import com.ecommerce.bex.exception.UserNotFoundException;
import com.ecommerce.bex.mapper.CartMapper;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final AuthenticationInformation authenticationInformation;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Transactional
    public CartResponseDTO addItem(CartAddRequestDTO dto){
        User user = authenticationInformation.getAuthenticatedUser();
        Cart cart = findCartEntity(user.getId());
        Product product = productRepository.findById(dto.productId()).orElseThrow(ProductNotFoundException::new);
        cart.addProduct(product, dto.qtd());
        cartRepository.save(cart);
        return cartMapper.toResponse(cart);
    }

    public PageResponseDTO<CartResponseDTO> findAll(Pageable pageable){
        Page<CartResponseDTO> carts = cartRepository.findAll(pageable).map(cartMapper::toResponse);
        return PageResponseDTO.fromPage(carts);
    }

    public PageResponseDTO<ItemResponseDTO> findMyProducts(Pageable pageable){
        Cart cart = findCartEntity(authenticationInformation.getAuthenticatedUser().getId());
        Page<ItemResponseDTO> myProducts = cartRepository.findProductInCart(pageable, cart.getId()).map(cartMapper::toItemResponse);
        return PageResponseDTO.fromPage(myProducts);
    }

    public PageResponseDTO<ItemResponseDTO> findCartProducts(Pageable pageable, Long id){
        Page<ItemResponseDTO> cartProducts = cartRepository.findProductInCart(pageable, id).map(cartMapper::toItemResponse);
        return PageResponseDTO.fromPage(cartProducts);
    }

    private Cart findCartEntity(Long userId){
        return cartRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }
}
