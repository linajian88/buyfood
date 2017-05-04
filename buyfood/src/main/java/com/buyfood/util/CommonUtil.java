package com.buyfood.util;

import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

public class CommonUtil {
	/**
	 * @author jipeng
	 * @return
	 * 
	 * 		返回json格式
	 * 
	 */
	public static JSONObject constructResponse(int code, String msg, Object data) {
		JSONObject jo = new JSONObject();
		jo.put("code", code);
		jo.put("msg", msg);
		jo.put("data", data);
		return jo;
	}

	public static String GUID() {
		String a = null;

		// 产生 5 个 GUID
		for (int i = 0; i < 5; i++) {
			// 创建 GUID 对象
			UUID uuid = UUID.randomUUID();
			// 得到对象产生的ID
			a = uuid.toString();
			// 转换为大写
			a = a.toUpperCase();
			// 替换 -
			a = a.replaceAll("-", "_");
			// System.out.println(a);
		}
		System.out.println(a + "a");
		return a;
	}

}
