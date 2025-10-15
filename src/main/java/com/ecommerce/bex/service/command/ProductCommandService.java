package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.product.*;
import com.ecommerce.bex.enums.ProductCategory;
import com.ecommerce.bex.event.product.ProductCreatedEvent;
import com.ecommerce.bex.event.product.ProductDeletedEvent;
import com.ecommerce.bex.event.product.ProductPriceChangedEvent;
import com.ecommerce.bex.event.product.ProductStockUpdatedEvent;
import com.ecommerce.bex.exception.ProductNotFoundException;
import com.ecommerce.bex.mapper.ProductMapper;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.ProductInformation;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.ProductRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import com.ecommerce.bex.service.EventStoreService;
import com.ecommerce.bex.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final EventStoreService eventStoreService;
    private final AuthenticationInformation authenticationInformation;

    @CacheEvict(value = {"products", "products-category"}, allEntries = true)
    public Long create(CreateProductCommand command){
        ProductInformation productInformation = productMapper.toInformation(command);
        ProductCategory category = ProductCategory.fromString(command.category());
        Product product = new Product(productInformation, category);
        Product savedProduct = productRepository.save(product);
        ProductCreatedEvent event = new ProductCreatedEvent(product);
        eventStoreService.saveEvent(AppConstants.AGGREGATE_PRODUCT_TYPE, product.getId(), event);
        return savedProduct.getId();
    }

    @CacheEvict(value = {"products", "products-category"}, allEntries = true)
    public void delete(DeleteProductCommand command){
        Product product = getProduct(command.productId());
        productRepository.delete(product);
        ProductDeletedEvent event = new ProductDeletedEvent(command.productId(), getAuthenticatedUser().getId(), product.getProductName(), command.reason());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_PRODUCT_TYPE, command.productId(), event);
    }

    @CacheEvict(value = "products", key = "#command.productId")
    public void decreaseStock(DecreaseStockCommand command){
        Product product = getProduct(command.productId());
        Integer oldStock = product.getStock();
        product.decreaseStock(command.quantity());
        productRepository.save(product);
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(product.getId(), AppConstants.SYSTEM_ID, oldStock, product.getStock());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_PRODUCT_TYPE, product.getId(), event);
    }

    @CacheEvict(value = "products", key = "#command.productId")
    public void updateName(UpdateProductNameCommand command){
        Product product = getProduct(command.productId());
        product.changeProductName(command.newName());
        productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#command.productId")
    public void updatePrice(UpdateProductPriceCommand command){
        Product product = getProduct(command.productId());
        BigDecimal oldPrice = product.getPrice();
        product.changeProductPrice(command.newPrice());
        productRepository.save(product);
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(product.getId(), getAuthenticatedUser().getId(), oldPrice, product.getPrice());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_PRODUCT_TYPE, product.getId(), event);
    }

    @CacheEvict(value = "products", key = "#command.productId")
    public void updateStock(UpdateProductStockCommand command){
        Product product = getProduct(command.productId());
        Integer oldStock = product.getStock();
        product.changeProductStock(command.newStock());
        productRepository.save(product);
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(product.getId(), getAuthenticatedUser().getId(), oldStock, product.getStock());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_PRODUCT_TYPE, product.getId(), event);
    }

    private Product getProduct(Long productId){
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    private User getAuthenticatedUser(){
        return authenticationInformation.getAuthenticatedUser();
    }
}
