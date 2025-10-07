package com.ecommerce.bex.service;

import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void create(){
        Product product = new Product("Testando2");
        productRepository.save(product);
    }
}
