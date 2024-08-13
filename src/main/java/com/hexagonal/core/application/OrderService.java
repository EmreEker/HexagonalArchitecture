package com.hexagonal.core.application;

import java.util.List;
import java.util.Optional;

import com.hexagonal.core.domain.model.Order;
import com.hexagonal.core.domain.usecase.OrderDomainUsecase;

public class OrderService {

	private final OrderDomainUsecase orderDomainService;

	public OrderService(OrderDomainUsecase orderDomainService) {
		this.orderDomainService = orderDomainService;
	}

	public Optional<Order> findOrderById(Long id) {
		return orderDomainService.findOrderById(id);
	}

	public List<Order> findAllOrders() {
		return orderDomainService.findAllOrders();
	}

	public Order saveOrder(Order order) {
		return orderDomainService.saveOrder(order);
	}

}
