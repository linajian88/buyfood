package com.buyfood.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buyfood.dao.BuylistMapper;
import com.buyfood.dao.FoodMapper;
import com.buyfood.dao.OrderMapper;
import com.buyfood.dao.OrderlistMapper;
import com.buyfood.model.Order;
import com.buyfood.model.OrderlistCustom;
import com.buyfood.service.OrderlistService;

@Service
public class OrderlistServiceImpl implements OrderlistService {

	@Autowired
	OrderlistMapper orderlistMapper;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	FoodMapper foodMapper;
	@Autowired
	BuylistMapper buylistMapper;

	public void insertOrderlist(OrderlistCustom orderlistCustom) throws Exception {
		orderlistMapper.insertOrderlist(orderlistCustom);
	}

	public List<OrderlistCustom> getAllOrderlist(int id) throws Exception {

		List<OrderlistCustom> orderlist = orderlistMapper.getAllOrderlist(id);

		if (orderlist == null) {
			return null;
		}
		for (OrderlistCustom item : orderlist) {
			String uid = item.getUid();

			item.setOrders(orderMapper.getAllOrder(uid));
		}
		return orderlist;
	}

}
