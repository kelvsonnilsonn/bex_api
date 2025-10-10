package com.ecommerce.bex.service;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.product.ProductCreateRequestDTO;
import com.ecommerce.bex.dto.product.ProductResponseDTO;
import com.ecommerce.bex.enums.ProductCategory;
import com.ecommerce.bex.exception.InvalidCategoryException;
import com.ecommerce.bex.exception.ProductNotFoundException;
import com.ecommerce.bex.mapper.ProductMapper;
import com.ecommerce.bex.model.CartItem;
import com.ecommerce.bex.model.Product;
import com.ecommerce.bex.model.ProductInformation;
import com.ecommerce.bex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO create(ProductCreateRequestDTO dto){
        ProductInformation productInformation = productMapper.toInformation(dto);
        ProductCategory category = selectCategory(dto.category());
        Product product = new Product(productInformation, category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    public PageResponseDTO<ProductResponseDTO> findAll(Pageable pageable){
        Page<ProductResponseDTO> products = productRepository.findAll(pageable).map(productMapper::toResponse);
        return PageResponseDTO.fromPage(products);
    }

    public ProductResponseDTO findById(Long id){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.toResponse(product);
    }

    public PageResponseDTO<ProductResponseDTO> findByCategory(Pageable pageable, String category){
        ProductCategory selectedCategory = selectCategory(category);
        Page<ProductResponseDTO> products = productRepository.findByCategory(pageable, selectedCategory).map(productMapper::toResponse);
        return PageResponseDTO.fromPage(products);
    }

    public void decreaseStock(Product product, int quantity){
        product.decreaseStock(quantity);
        productRepository.save(product);
    }

    private ProductCategory selectCategory(String category) {
        try{
            return ProductCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException();
        }
    }
}
