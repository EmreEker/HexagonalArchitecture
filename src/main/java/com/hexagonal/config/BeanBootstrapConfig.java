package com.hexagonal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.core.application.OrderService;
import com.hexagonal.core.application.ProductService;
import com.hexagonal.core.domain.repository.OrderRepository;
import com.hexagonal.core.domain.repository.ProductRepository;
import com.hexagonal.core.domain.usecase.OrderDomainUsecase;
import com.hexagonal.core.domain.usecase.ProductDomainUsecase;

@Configuration
public class BeanBootstrapConfig {

	@Bean
    public OrderDomainUsecase orderDomainService(OrderRepository orderRepository) {
        return new OrderDomainUsecase(orderRepository);
    }

    @Bean
    public ProductDomainUsecase productDomainService(ProductRepository productRepository) {
        return new ProductDomainUsecase(productRepository);
    }

    @Bean
    public OrderService orderService(OrderDomainUsecase orderDomainService) {
        return new OrderService(orderDomainService);
    }

    @Bean
    public ProductService productService(ProductDomainUsecase productDomainService) {
        return new ProductService(productDomainService);
    }
}
