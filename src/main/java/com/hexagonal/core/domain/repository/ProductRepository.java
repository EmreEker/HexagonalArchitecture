package com.hexagonal.core.domain.repository;

import java.util.List;
import java.util.Optional;

import com.hexagonal.core.domain.model.Product;

public interface ProductRepository {
    Optional<Product> findDomainProductById(Long id);
    List<Product> findAllDomainProducts();
    Product saveDomainProduct(Product product);
}