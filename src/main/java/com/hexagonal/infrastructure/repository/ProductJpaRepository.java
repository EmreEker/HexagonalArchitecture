package com.hexagonal.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexagonal.core.domain.model.Product;
import com.hexagonal.core.domain.repository.ProductRepository;
import com.hexagonal.infrastructure.persistence.ProductEntity;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>,ProductRepository {

 
    default Optional<Product> findDomainProductById(Long id) {
        return findById(id).map(this::toDomainModel);
    }

   
    default List<Product> findAllDomainProducts() {
        return findAll().stream()
            .map(this::toDomainModel)
            .collect(Collectors.toList());
    }
  
    default Product saveDomainProduct(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity savedEntity = save(entity);
        return toDomainModel(savedEntity);
    }


    private Product toDomainModel(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }

    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        return entity;
    }
}
