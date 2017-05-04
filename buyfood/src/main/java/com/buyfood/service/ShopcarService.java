package com.buyfood.service;

import java.util.List;

import com.buyfood.model.BuylistE;

public interface ShopcarService {
	List<BuylistE> getAllList(int userId);
	void addBuyList(int userId,int foodId,int foodNum);
	void delBuyList(int buyId);
	void changeBuyList(int buyId,int foodNum);
	void updateBuyListStatu(int status);
	List<BuylistE> getListByUserId(int userid);
}
