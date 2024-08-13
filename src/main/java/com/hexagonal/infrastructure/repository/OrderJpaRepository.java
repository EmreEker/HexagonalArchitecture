package com.hexagonal.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexagonal.core.domain.model.Order;
import com.hexagonal.core.domain.model.Product;
import com.hexagonal.core.domain.repository.OrderRepository;
import com.hexagonal.infrastructure.persistence.OrderEntity;
import com.hexagonal.infrastructure.persistence.ProductEntity;

@Repository
public interface OrderJpaRepository extends OrderRepository, JpaRepository<OrderEntity, Long> {

	@Override
	default Optional<Order> findOrderDomainId(Long id) {
		return findById(id).map(this::toDomainModel);

	}

	@Override
	default List<Order> findAllDomainOrders() {
		return findAll().stream().map(this::toDomainModel).collect(Collectors.toList());
	}

	@Override
	default Order saveDomainOrder(Order order) {
		OrderEntity entity = toEntity(order);
		OrderEntity savedEntity = save(entity);
		return toDomainModel(savedEntity);
	}

	private Order toDomainModel(OrderEntity entity) {
		List<Product> products = entity.getProducts().stream().map(
				productEntity -> new Product(productEntity.getId(), productEntity.getName(), productEntity.getPrice()))
				.collect(Collectors.toList());

		return new Order(entity.getId(), products, entity.getOrderDate(),entity.getShippingCost(),entity.getTotalAmount());
	}

	private OrderEntity toEntity(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.setId(order.getId());
		entity.setOrderDate(order.getOrderDate());
		entity.setProducts(order.getProducts().stream().map(product -> {
			ProductEntity productEntity = new ProductEntity();
			productEntity.setId(product.getId());
			productEntity.setName(product.getName());
			productEntity.setPrice(product.getPrice());
			return productEntity;
		}).collect(Collectors.toList()));
		return entity;
	}
}
