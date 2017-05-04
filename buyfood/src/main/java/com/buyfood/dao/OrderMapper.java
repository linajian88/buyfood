package com.buyfood.dao;

import java.util.List;

import com.buyfood.model.Order;

public interface OrderMapper {
	
	//添加订单
	public void insertOrder(Order order);
	//取得所有订单
	public List<Order> getAllOrder(String orderid);
}
