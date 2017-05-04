package com.buyfood.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buyfood.dao.FoodMapper;
import com.buyfood.model.Food;
import com.buyfood.service.FoodService;

@Service
public class FoodServicelmpl implements FoodService {

	@Autowired
	FoodMapper foodMapper;

	@Override
	public List<Food> getAllFoodInfo() throws Exception {
		List<Food> list = foodMapper.getAllFoodInfo();
		return list;
	}

	@Override
	public void addFood(Food food) throws Exception {
		foodMapper.addFood(food);
	}

	@Override
	public void deleteFoodById(int id) throws Exception {
		foodMapper.deleteFoodById(id);
	}

	@Override
	public void updateFoodPrice(Food food) throws Exception {
		foodMapper.updateFoodPrice(food);
	}

	@Override
	public void updateFoodNum(Food food) throws Exception {
		foodMapper.updateFoodNum(food);
	}

	@Override
	public List<Food> getFoodByPage(int page) throws Exception {
		int begin = (page-1)*6 ; 
		List<Food> list = foodMapper.getFoodByPage(begin);
		return list;
	}

	public int getFoodCount() throws Exception{
		int count = foodMapper.getFoodCount();
		return count ;
	}
	
	public Food getFoodById(int id) throws Exception {
		return foodMapper.getFoodById(id);
	};

}
