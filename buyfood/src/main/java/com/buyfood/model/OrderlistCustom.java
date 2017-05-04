package com.buyfood.model;

import java.util.List;

//自定义包装类
public class OrderlistCustom extends Orderlist{
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
