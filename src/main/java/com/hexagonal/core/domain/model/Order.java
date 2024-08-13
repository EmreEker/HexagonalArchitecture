package com.hexagonal.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private Long id;
	private List<Product> products;
	private LocalDateTime orderDate;
	private BigDecimal shippingCost;
	private BigDecimal totalAmount;
	public Order() {
	}

	public Order(Long id, List<Product> products, LocalDateTime orderDate, BigDecimal shippingCost,BigDecimal totalAmount) {
		this.id = id;
		this.products = products;
		this.orderDate = orderDate;
		this.shippingCost = shippingCost;
		this.setTotalAmount(totalAmount);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}