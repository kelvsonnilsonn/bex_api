package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.cart.CartAddRequestDTO;
import com.ecommerce.bex.dto.cart.CartResponseDTO;
import com.ecommerce.bex.dto.cart.ItemResponseDTO;
import com.ecommerce.bex.service.CartService;
import com.ecommerce.bex.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.CART_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class CartController {

    private final CartService cartService;

    @PostMapping(AppConstants.ITEMS_SEARCH_PATH)
    public ResponseEntity<String> addItem(@RequestBody CartAddRequestDTO dto){
        return ResponseEntity.ok(cartService.addItem(dto));
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<PageResponseDTO<ItemResponseDTO>> findAllMyCartItems(Pageable pageable){
        return ResponseEntity.ok(cartService.findMyProducts(pageable));
    }

    @GetMapping(AppConstants.ALL_CARTS_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<CartResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(cartService.findAll(pageable));
    }

    @GetMapping(AppConstants.ID_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<ItemResponseDTO>> findCartProducts(Pageable pageable, @PathVariable Long id){
        return ResponseEntity.ok(cartService.findCartProducts(pageable, id));
    }
}
