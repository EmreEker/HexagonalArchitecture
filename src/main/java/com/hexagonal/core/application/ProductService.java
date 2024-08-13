package com.hexagonal.core.application;

import java.util.List;
import java.util.Optional;

import com.hexagonal.core.domain.model.Product;
import com.hexagonal.core.domain.usecase.ProductDomainUsecase;

public class ProductService {
	private final ProductDomainUsecase productDomainService;

	public ProductService(ProductDomainUsecase productDomainService) {
		this.productDomainService = productDomainService;
	}

	public Optional<Product> getProductById(Long id) {
		return productDomainService.findProductById(id);
	}

	public List<Product> getAllProducts() {
		return productDomainService.getAllProducts();
	}

	public Product createProduct(Product product) {
		return productDomainService.saveProduct(product);
	}

}