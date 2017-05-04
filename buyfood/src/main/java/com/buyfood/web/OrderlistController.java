package com.buyfood.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.buyfood.model.BuylistE;
import com.buyfood.model.Food;
import com.buyfood.model.Order;
import com.buyfood.model.OrderlistCustom;
import com.buyfood.model.User;
import com.buyfood.service.FoodService;
import com.buyfood.service.OrderService;
import com.buyfood.service.OrderlistService;
import com.buyfood.service.ShopcarService;
import com.buyfood.util.BaseController;
import com.buyfood.util.CommonUtil;
import com.buyfood.util.DateUtil;
import com.buyfood.util.StringUtil;

@Controller
public class OrderlistController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	OrderlistService orderlistService;

	@Autowired
	OrderService orderService;

	@Autowired
	FoodService foodService;

	@Autowired
	ShopcarService shopcarService;

	/**
	 * 
	 * @Title: addOrderlist
	 * @Description: 添加订单和订单列表，同时减少food库存
	 * @param 设定文件
	 * @return JSONObject 返回类型
	 */
	@RequestMapping(value = "/Orderlist/addOrderlist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addOrderlist(OrderlistCustom orderlistCustom, HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		int userid = user.getId();
		orderlistCustom.setUserid(String.valueOf(user.getId()));
		orderlistCustom.setUsername(user.getLoginname());
		// 验证电话号码是否合法
		String phoneNumber = orderlistCustom.getUsertel();
		if (!StringUtil.checkPhoneNumber(phoneNumber)) {
			return CommonUtil.constructResponse(-2, "号码格式错误", null);
		}

		// 生成一个UUID,设置为uid
		String uid = UUID.randomUUID().toString();
		orderlistCustom.setUid(uid);
		// 生成当前时间戳，设置addtime
		long addtime = DateUtil.getTimeStamp();
		orderlistCustom.setAddtime(addtime);
		// 订单列表状态stat(0为未付款，1为货到已付款)
		int stat = 0;
		orderlistCustom.setStat(stat);
		try {
			orderlistService.insertOrderlist(orderlistCustom);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-1, "数据库错误", null);
		}
		// 取得购物车里的喜欢
		List<BuylistE> list = shopcarService.getListByUserId(userid);

		for (BuylistE buy : list) {
			Order order = new Order();
			try {
				Food foodinfo = foodService.getFoodById(buy.getFoodID());

				order.setName(foodinfo.getName());
				order.setFoodid(foodinfo.getId());
				order.setNum(buy.getFoodNum());
				order.setOrderid(uid);
				order.setPrice(foodinfo.getPrice());
				order.setTotal(order.getPrice() * order.getNum());
				orderService.insertOrder(order);
				// 修改购物车状态
				shopcarService.updateBuyListStatu(buy.getId());
			} catch (Exception e) {
				return CommonUtil.constructResponse(-2, "库存不足", null);
			}
		}

		return CommonUtil.constructResponse(1, "请求成功", null);
	}

	/**
	 * 
	 * @Title: getAllOrderlist
	 * @Description: 所有订单列表的查询，包括所有的订单
	 * @param @return
	 *            设定文件
	 * @return JSONObject 返回类型
	 */
	@RequestMapping(value = "/Orderlist/getAllOrderlist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllOrderlist(HttpServletRequest request) {
		List<OrderlistCustom> list = new ArrayList<OrderlistCustom>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			list = orderlistService.getAllOrderlist(user.getId());
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库异常", null);
		}
		return CommonUtil.constructResponse(1, "请求成功", list);
	}
}
