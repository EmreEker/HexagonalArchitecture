package com.hexagonal.core.domain.usecase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.hexagonal.core.domain.model.Order;
import com.hexagonal.core.domain.repository.OrderRepository;

public class OrderDomainUsecase {

    private final OrderRepository orderRepository;

    public OrderDomainUsecase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findOrderById(Long id) {
        Optional<Order> order = orderRepository.findOrderDomainId(id);
        return order.map(this::applyOrderRules);
    }

    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAllDomainOrders();
        return applyBulkOrderRules(orders);
    }

    public Order saveOrder(Order order) {
        Order processedOrder = applyOrderRules(order);
        return orderRepository.saveDomainOrder(processedOrder);
    }

    private Order applyOrderRules(Order order) {
        if (order.getTotalAmount().compareTo(new BigDecimal("500.00")) > 0) {
            applyFreeShipping(order);
        }
        return order;
    }

    private List<Order> applyBulkOrderRules(List<Order> orders) { 
        orders.forEach(this::applyOrderRules);
        return orders;
    }

    private void applyFreeShipping(Order order) {
        order.setShippingCost(BigDecimal.ZERO);
    }
}
