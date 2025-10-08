package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.product.ProductCreateRequestDTO;
import com.ecommerce.bex.dto.product.ProductResponseDTO;
import com.ecommerce.bex.service.ProductService;
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
@PreAuthorize("permitAll()")
public class ProductController implements ProductAPI {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER_ROLE') or hasRole('ADMIN_ROLE')")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductCreateRequestDTO dto){
        ProductResponseDTO response = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping(AppConstants.ID_PATH)
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping(AppConstants.CATEGORY_SEARCH_PATH)
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> findByCategory(Pageable pageable, @PathVariable String productCategory){
        return ResponseEntity.ok(productService.findByCategory(pageable, productCategory));
    }
}
