package com.buyfood.dao;

import java.util.List;
import java.util.Map;

import com.buyfood.model.BuylistE;


public interface BuylistMapper {
	public List<BuylistE> getAllList(int userId);
	public void addBuyList(Map<String,Object> map);
	public void delBuyList(int buyId);
	public void changeBuyList(Map<String,Object> map);
	public void updateBuyListStatu(int status) ;
	public List<BuylistE> getListByUserId(int userid);
}
