package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.product.CreateProductCommand;
import com.ecommerce.bex.command.product.DecreaseStockCommand;
import com.ecommerce.bex.enums.ProductCategory;
import com.ecommerce.bex.exception.ProductNotFoundException;
import com.ecommerce.bex.mapper.ProductMapper;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.ProductInformation;
import com.ecommerce.bex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Long create(CreateProductCommand command){
        ProductInformation productInformation = productMapper.toInformation(command);
        ProductCategory category = ProductCategory.fromString(command.category());
        Product product = new Product(productInformation, category);
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public void decreaseStock(DecreaseStockCommand command){
        Product product = productRepository.findById(command.id()).orElseThrow(ProductNotFoundException::new);
        product.decreaseStock(command.quantity());
        productRepository.save(product);
    }
}
