package com.buyfood.dao;

import java.util.List;

import com.buyfood.model.Orderlist;
import com.buyfood.model.OrderlistCustom;

public interface OrderlistMapper {
	// 创建订单列表
	public void insertOrderlist(Orderlist orderlist);

	// 取得所有订单列表
	public List<OrderlistCustom> getAllOrderlist(int id);

}
