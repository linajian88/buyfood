package com.buyfood.service;

import java.util.List;

import com.buyfood.model.Order;

public interface OrderService {
	
		//添加订单
		public void insertOrder(Order order) throws Exception;
		//取得所有订单
		public List<Order> getAllOrder(String uid) throws Exception;
}
