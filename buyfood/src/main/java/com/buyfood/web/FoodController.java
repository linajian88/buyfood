package com.buyfood.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.buyfood.model.Food;
import com.buyfood.service.FoodService;
import com.buyfood.util.CommonUtil;
import com.buyfood.util.DateUtil;
import com.buyfood.util.PicUploadUtil;

@Controller
public class FoodController {
	@Autowired
	FoodService foodService;

	/**
	 * 
	 * @Title: getAllFoodInfo
	 * @Description: 获得所有Food详情
	 * @return JSONObject JSON
	 */
	@RequestMapping(value = "/Food/getAllFoodInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllFoodInfo() {
		try {
			List<Food> list = foodService.getAllFoodInfo();
			return CommonUtil.constructResponse(1, "请求成功", list);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}
	}

	/**
	 * 
	 * @Title: addFood
	 * @Description: 用于添加Food
	 * @param food:
	 *            需要name , price , info .addtime , pic , type , num
	 * @return JSONObject 返回类型
	 */
	@RequestMapping(value = "/food/addFood", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addFood(Food food, HttpServletRequest request) {
		// 添加时间
		food.setAddtime(DateUtil.getTimeStamp());
		try {
			foodService.addFood(food);
			return CommonUtil.constructResponse(1, "请求成功", null);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}
	}

	@RequestMapping("food/foodpicupload")
	@ResponseBody
	public JSONObject userpicUpload(HttpServletRequest request, HttpServletResponse response) {
		// 上传后返回情况说明
		String result = null;
		// 上传图片路径
		String path = null;

		// 创建一个通用的多部分解析器
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		// //判断 request 是否有文件上传,即多部分请求
		if (cmr.isMultipart(request)) {
			// 转换成多部分
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			// //取得request中的所有文件名
			Iterator<String> iter = mhsr.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = mhsr.getFile((String) iter.next());

				// //取得当前上传文件的文件名称
				String filename = file.getOriginalFilename();
				// 获得文件后缀
				String fileSuffixName = filename.substring(filename.indexOf("."));

				if (file.getSize() > 1048576) {
					result = "上传失败：上传文件大小大于1M";
					return CommonUtil.constructResponse(0, result, null);

				} else if (!fileSuffixName.equals(".jpg") && !fileSuffixName.equals(".png")
						&& !fileSuffixName.equals(".JPG") && !fileSuffixName.equals(".JPEG")
						&& !fileSuffixName.equals(".bmp") && !fileSuffixName.equals(".jpeg")
						&& !fileSuffixName.equals(".gif")) {
					result = "上传失败：上传文件类型不正确";
					return CommonUtil.constructResponse(0, result, null);
				}

				// 生成的GUID为一串32位字符组成的128位数据上传文件重命名
				CommonUtil cu = new CommonUtil();
				String UUID = cu.GUID();

				String filename1 = UUID + fileSuffixName;
				path = request.getSession().getServletContext().getRealPath("/image");
				File file3 = new File(path + "/" + filename1);
				if (!file3.exists()) {
					file3.mkdir();
				}
				try {
					// transfer方法是MultipartFile包中提供的方法，直接可以写入文件到指定目录
					file.transferTo(file3);
					JSONObject jo = new JSONObject();
					jo.put("pic", "../image/" + filename1);
					System.out.println(jo);
					return CommonUtil.constructResponse(1, "成功", jo);
				} catch (Exception e) {
					result = e.getMessage();
					e.printStackTrace();
					return CommonUtil.constructResponse(0, result, null);
				}
			}
		}
		return CommonUtil.constructResponse(0, null, null);

	}

	/**
	 * 
	 * @Title: updateFoodInfo
	 * @Description: 修改商品信息（商品价格和数量）
	 * @param @param
	 *            food
	 * @param @return
	 *            设定文件
	 * @return JSONObject 返回类型
	 */
	@RequestMapping(value = "/Food/updateFoodInfo", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateFoodInfo(Food food) {
		try {
			foodService.updateFoodPrice(food);
			foodService.updateFoodNum(food);
			return CommonUtil.constructResponse(1, "请求成功", null);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}
	}

	/**
	 * 
	 * @Title: deleteFoodById
	 * @Description: 通过id删除商品
	 * @param @param
	 *            id 商品id
	 * @param @return
	 *            设定文件
	 * @return JSONObject 返回类型
	 */
	@RequestMapping(value = "/Food/deleteFoodById", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteFoodById(int id) {
		try {
			foodService.deleteFoodById(id);
			return CommonUtil.constructResponse(1, "请求成功", null);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}
	}

	/**
	 * 
	 * @Title: getFoodByPage
	 * @Description: 分页
	 * @param @param
	 *            page
	 * @param @return
	 *            设定文件
	 * @return JSONObject 返回类型
	 */
	@RequestMapping(value = "/Food/getFoodByPage", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getFoodByPage(int page, HttpServletRequest request) {
		try {
			List<Food> list = foodService.getFoodByPage(page);
			int count = foodService.getFoodCount();
			int allpage;

			
			if (count%6>0) {
				allpage = count / 6 + 1;
			} else {
				allpage = count / 6;
			}
			JSONObject jo = new JSONObject();
			if (request.getSession().getAttribute("user") == null) {
				jo.put("code", 1);
			} else {
				jo.put("code", 2);
			}
			jo.put("msg", "请求成功");
			jo.put("data", list);
			jo.put("allpage", allpage);
			return jo;
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}
	}
}
