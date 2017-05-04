package com.buyfood.service;

import java.util.List;

import com.buyfood.model.Food;

public interface FoodService {
	
	//获得所有的商品信息(8个)
	public List<Food> getAllFoodInfo() throws Exception ;
	
	//更新商品信息（价格）(需要id)
	public void updateFoodPrice(Food food) throws Exception;
	
	//添加商品(所有信息)
	public void addFood(Food food) throws Exception;
	
	//通过id删除商品
	public void deleteFoodById(int id) throws Exception ;
	
	//更新food库存
	public void updateFoodNum(Food food) throws Exception ;
	
	//分页获取商品信息（前端首页）
	public List<Food> getFoodByPage(int page) throws Exception;
	
	//取得food数量
	public int getFoodCount() throws Exception;
	
	public Food getFoodById(int id) throws Exception ;
}
