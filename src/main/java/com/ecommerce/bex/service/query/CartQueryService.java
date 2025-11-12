package com.ecommerce.bex.service.query;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.CartResponseDTO;
import com.ecommerce.bex.dto.ItemResponseDTO;
import com.ecommerce.bex.exception.user.UserNotFoundException;
import com.ecommerce.bex.mapper.CartMapper;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartQueryService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final AuthenticationInformation authenticationInformation;

    @Cacheable("cart-products")
    public PageResponseDTO<ItemResponseDTO> findMyCartProducts(Pageable pageable) {
        System.out.println("🎯 BUSCANDO PRODUTOS DO CARRINHO NO BANCO");
        Cart cart = findCartEntity();
        Page<ItemResponseDTO> items = cartRepository.findProductInCart(pageable, cart.getId())
                .map(cartMapper::toItemResponse);
        return PageResponseDTO.fromPage(items);
    }

    public PageResponseDTO<ItemResponseDTO> findCartProducts(Pageable pageable, Long id){
        Page<ItemResponseDTO> cartProducts = cartRepository.findProductInCart(pageable, id).map(cartMapper::toItemResponse);
        return PageResponseDTO.fromPage(cartProducts);
    }

    public PageResponseDTO<CartResponseDTO> findAll(Pageable pageable){
        Page<CartResponseDTO> carts = cartRepository.findAll(pageable).map(cartMapper::toResponse);
        return PageResponseDTO.fromPage(carts);
    }

    private Cart findCartEntity() {
        User user = authenticationInformation.getAuthenticatedUser();
        return cartRepository.findByUserId(user.getId())
                .orElseThrow(UserNotFoundException::new);
    }
}
