package com.hexagonal.core.domain.usecase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.hexagonal.core.domain.model.Product;
import com.hexagonal.core.domain.repository.ProductRepository;

public class ProductDomainUsecase {

    private final ProductRepository productRepository;

    public ProductDomainUsecase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductById(Long id) {
        Optional<Product> product = productRepository.findDomainProductById(id);
        return product.map(this::applyDiscountIfApplicable);
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAllDomainProducts();
        return applyBulkDiscounts(products);
    }

    public Product saveProduct(Product product) {
        Product processedProduct = calculateTax(product);
        return productRepository.saveDomainProduct(processedProduct);
    }

    private Product applyDiscountIfApplicable(Product product) {
       
        if (product.getPrice().compareTo(new BigDecimal("100.00")) > 0) {
            product.setPrice(product.getPrice().multiply(new BigDecimal("0.9"))); 
        }
        return product;
    }

    private List<Product> applyBulkDiscounts(List<Product> products) {
        products.forEach(this::applyDiscountIfApplicable);
        return products;
    }

    private Product calculateTax(Product product) {
       
        BigDecimal taxRate = new BigDecimal("0.18"); 
        BigDecimal taxAmount = product.getPrice().multiply(taxRate);
        product.setPrice(product.getPrice().add(taxAmount));
        return product;
    }
}
