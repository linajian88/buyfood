package com.buyfood.model;

import org.springframework.stereotype.Component;

@Component(value = "food")
public class Food {
	private int id;// 食品编号
	private String name;// 食品名字
	private int price;// 食品价格
	private String info;// 食品信息
	private long addtime;// 添加时间
	private String pic;// 食品图片地址
	private int type;// 食品类型
	private int num;// 食品剩余数量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Food(int id, String name, int price, String info, long addtime, String pic, int type, int num) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.info = info;
		this.addtime = addtime;
		this.pic = pic;
		this.type = type;
		this.num = num;
	}

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

}
