package com.ecommerce.bex.service.query;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.ProductResponseDTO;
import com.ecommerce.bex.enums.ProductCategory;
import com.ecommerce.bex.exception.ProductNotFoundException;
import com.ecommerce.bex.mapper.ProductMapper;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PageResponseDTO<ProductResponseDTO> findAll(Pageable pageable){
        Page<ProductResponseDTO> products = productRepository.findAll(pageable).map(productMapper::toResponse);
        return PageResponseDTO.fromPage(products);
    }

    @Cacheable("products")
    public ProductResponseDTO findById(Long productId){
        System.out.println("🎯 BUSCANDO PRODUTO NO BANCO: " + productId);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return productMapper.toResponse(product);
    }

    @Cacheable("products-category")
    public PageResponseDTO<ProductResponseDTO> findByCategory(Pageable pageable, String category){
        ProductCategory selectedCategory = ProductCategory.fromString(category);
        Page<ProductResponseDTO> products = productRepository.findByCategory(pageable, selectedCategory).map(productMapper::toResponse);
        return PageResponseDTO.fromPage(products);
    }
}
