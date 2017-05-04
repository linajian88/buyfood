package com.buyfood.service;

import java.util.List;

import com.buyfood.model.OrderlistCustom;

public interface OrderlistService {
	// 创建订单列表
	public void insertOrderlist(OrderlistCustom orderlistCustom) throws Exception;

	// 取得所有订单列表
	public List<OrderlistCustom> getAllOrderlist(int id) throws Exception;

}
