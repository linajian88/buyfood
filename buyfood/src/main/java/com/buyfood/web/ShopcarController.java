package com.buyfood.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.buyfood.model.BuylistE;
import com.buyfood.model.User;
import com.buyfood.service.ShopcarService;
import com.buyfood.util.BaseController;
import com.buyfood.util.CommonUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/Shopcar", method = RequestMethod.POST)
public class ShopcarController extends BaseController{
	private static final long serialVersionUID = 1L;
	
	@Resource(name="shopcarService")
	private ShopcarService shopcarService;
	
	@RequestMapping(value = "/getBuylist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getBuylist(HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<BuylistE> buyList = new ArrayList<BuylistE>();
		if(user !=null){
			try {
				buyList = shopcarService.getAllList(user.getId());
			} catch (Exception e) {
				return CommonUtil.constructResponse(-5, "数据库异常", buyList);
			}
			return CommonUtil.constructResponse(1, "成功", buyList);
		}else{
			return CommonUtil.constructResponse(-4, "用户没有登录", null);
		}	
	}
	
	@RequestMapping(value = "/addBuylist", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addBuylist(HttpSession session,int foodId,int foodNum) {
		User user = (User)session.getAttribute("user");
		if(user != null){
			try{
				shopcarService.addBuyList(user.getId(), foodId, foodNum);
			}catch (Exception e){
				return CommonUtil.constructResponse(-5, "数据库异常",null);
			}
			return CommonUtil.constructResponse(1, "成功", null);
		}else{
			return CommonUtil.constructResponse(-4, "用户没有登录", null);
		}	
	}
	
	@RequestMapping(value = "/delBuyList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delBuyList(int buyId){
		try {
			shopcarService.delBuyList(buyId);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库异常", null);
		}
		return CommonUtil.constructResponse(1, "成功",null);
	}
	
	@RequestMapping(value = "/changeBuyList", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject changeBuyList(int buyId,int foodNum){
		try {
			shopcarService.changeBuyList(buyId, foodNum);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库异常", null);
		}
		return CommonUtil.constructResponse(1, "成功",null);
	}
	
}
