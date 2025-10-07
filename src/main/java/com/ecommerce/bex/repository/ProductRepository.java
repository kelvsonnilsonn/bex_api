package com.ecommerce.bex.repository;

import com.ecommerce.bex.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
