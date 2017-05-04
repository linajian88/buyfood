package com.buyfood.model;

import org.springframework.stereotype.Component;

@Component(value="buylist")
public class BuylistE {
	private int id;//在购物车中的编号
	private int userid;//添加此条目的用户id
	private int foodID;//食品编号
	private int foodNum;//食品想买数量
	private long addtime;//此条目添加时间
	private int state;//状态
	private Food listFood;//食品
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public long getAddtime() {
		return addtime;
	}
	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Food getListFood() {
		return listFood;
	}
	public void setListFood(Food listFood) {
		this.listFood = listFood;
	}
	public BuylistE(int id, int userid, int foodID, int foodNum, long addtime, int state, Food listFood) {
		super();
		this.id = id;
		this.userid = userid;
		this.foodID = foodID;
		this.foodNum = foodNum;
		this.addtime = addtime;
		this.state = state;
		this.listFood = listFood;
	}
	public BuylistE() {
		super();
		// TODO Auto-generated constructor stub
	}
	


 }
