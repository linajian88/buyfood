package com.buyfood.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buyfood.dao.FoodMapper;
import com.buyfood.dao.OrderMapper;
import com.buyfood.model.Order;
import com.buyfood.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper ;
	
	@Autowired
	FoodMapper foodMapper;
	
	public void insertOrder(Order order) throws Exception{
		
		// 判断库存数量，如果数量大于订单数量，允许插入，否则抛出自定义异常
		int food_num = foodMapper.getFoodNumById(order.getFoodid());
		if (food_num < order.getNum()) {
			throw new Exception("库存不足");
		}
		foodMapper.decFoodNum(order);
		orderMapper.insertOrder(order);
	}

	public void deleteOrderByUid(String uid) throws Exception {
			
	}

	public List<Order> getAllOrder(String uid) throws Exception {
		List<Order> list = orderMapper.getAllOrder(uid);
		return list;
	}
	
}
