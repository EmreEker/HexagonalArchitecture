package com.hexagonal.core.domain.repository;

import java.util.List;
import java.util.Optional;

import com.hexagonal.core.domain.model.Order;

public interface OrderRepository {
    Optional<Order> findOrderDomainId(Long id);
    List<Order> findAllDomainOrders();
    Order saveDomainOrder(Order order);
}