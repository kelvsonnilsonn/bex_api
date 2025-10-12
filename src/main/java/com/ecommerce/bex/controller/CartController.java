package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.cart.AddItemToCartCommand;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.CartResponseDTO;
import com.ecommerce.bex.dto.ItemResponseDTO;
import com.ecommerce.bex.service.command.CartCommandService;
import com.ecommerce.bex.service.query.CartQueryService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
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

    private final CartCommandService commandService;
    private final CartQueryService queryService;

    @PostMapping(AppConstants.ITEMS_SEARCH_PATH)
    public ResponseEntity<Void> addItem(@RequestBody @Valid AddItemToCartCommand command){
        commandService.addItemToCart(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<PageResponseDTO<ItemResponseDTO>> findAllMyCartItems(Pageable pageable){
        return ResponseEntity.ok(queryService.findMyProducts(pageable));
    }

    @GetMapping(AppConstants.ALL_DATA_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<CartResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(queryService.findAll(pageable));
    }

    @GetMapping(AppConstants.ID_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<ItemResponseDTO>> findCartProducts(Pageable pageable, @PathVariable Long id){
        return ResponseEntity.ok(queryService.findCartProducts(pageable, id));
    }
}
