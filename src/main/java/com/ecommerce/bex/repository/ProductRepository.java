package com.ecommerce.bex.repository;

import com.ecommerce.bex.enums.ProductCategory;
import com.ecommerce.bex.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    Page<Product> findByCategory(Pageable pageable, ProductCategory category);

    Page<Product> findAllByOrderByVisitsDesc(Pageable pageable);
}
