package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.product.*;
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

    @DeleteMapping
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> delete(@RequestBody @Valid DeleteProductCommand command){
        commandService.delete(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(AppConstants.UPDATE_NAME_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> updateName(@RequestBody @Valid UpdateProductNameCommand command){
        commandService.updateName(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(AppConstants.UPDATE_PRICE_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> updatePrice(@RequestBody @Valid UpdateProductPriceCommand command){
        commandService.updatePrice(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(AppConstants.UPDATE_STOCK_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> updateStock(@RequestBody @Valid UpdateProductStockCommand command){
        commandService.updateStock(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<?> findProduct(Pageable pageable,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String productCategory){
        if(id != null){
            return ResponseEntity.ok(queryService.findById(id));
        }
        if(productCategory != null){
            return ResponseEntity.ok(queryService.findByCategory(pageable, productCategory));
        }
        return ResponseEntity.ok(queryService.findAll(pageable));
    }
}
