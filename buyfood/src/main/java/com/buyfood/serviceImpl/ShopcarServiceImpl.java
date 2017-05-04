package com.buyfood.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.buyfood.dao.BuylistMapper;
import com.buyfood.model.BuylistE;
import com.buyfood.service.ShopcarService;
import com.buyfood.util.DateUtil;

@Service("shopcarService")
public class ShopcarServiceImpl implements ShopcarService{
	@Resource(name="buylistMapper")
	private BuylistMapper buylistMapper;
	
	/**
	 * 获取用户购物车所有商品
	 */
	public List<BuylistE> getAllList(int userId) {
		return buylistMapper.getAllList(userId);
	}

	@Override
	/**
	 * 添加购物车
	 */
	public void addBuyList(int userId, int foodId, int foodNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		long addTime = DateUtil.getTimeStamp();
		map.put("userId",userId);
		map.put("foodId", foodId);
		map.put("foodNum",foodNum);
		map.put("addTime", addTime);
		buylistMapper.addBuyList(map);
	}

	@Override
	/**
	 * 删除购物车商品
	 */
	public void delBuyList(int buyId) {
		buylistMapper.delBuyList(buyId);
	}

	@Override
	/**
	 * 改变购物车信息
	 */
	public void changeBuyList(int buyId, int foodNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("buyId",buyId);
		map.put("foodNum",foodNum);
		buylistMapper.changeBuyList(map);
	}

	public void updateBuyListStatu(int status){
		buylistMapper.updateBuyListStatu(status);
	}

	@Override
	public List<BuylistE> getListByUserId(int userid) {
		return buylistMapper.getListByUserId(userid);
	}
}
