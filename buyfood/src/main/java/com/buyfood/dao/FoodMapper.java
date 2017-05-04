package com.buyfood.dao;

import java.util.List;

import com.buyfood.model.Food;
import com.buyfood.model.Order;

public interface FoodMapper {
	
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
	
	//减少food库存
	public void decFoodNum(Order order) throws Exception ;
	
	//通过id获得商品库存
	public int getFoodNumById(int id) throws Exception ;
	
	//通过page取得food
	public List<Food> getFoodByPage(int begin) throws Exception ;
	
	//获得food总数
	public int getFoodCount() throws Exception ;
	
	public Food getFoodById(int id) throws Exception ;
	
}
