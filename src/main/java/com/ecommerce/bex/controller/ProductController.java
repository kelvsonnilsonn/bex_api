package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.product.CreateProductCommand;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.ProductResponseDTO;
import com.ecommerce.bex.service.command.ProductCommandService;
import com.ecommerce.bex.service.query.ProductQueryService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.PRODUCT_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class ProductController implements ProductAPI {

    private final ProductCommandService commandService;
    private final ProductQueryService queryService;

    @PostMapping
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Long> create(@RequestBody @Valid CreateProductCommand command){
        Long productId = commandService.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(queryService.findAll(pageable));
    }

    @GetMapping(AppConstants.ID_PATH)
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(queryService.findById(id));
    }

    @GetMapping(AppConstants.CATEGORY_SEARCH_PATH)
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> findByCategory(Pageable pageable, @PathVariable String productCategory){
        return ResponseEntity.ok(queryService.findByCategory(pageable, productCategory));
    }
}
