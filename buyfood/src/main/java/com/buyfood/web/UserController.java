package com.buyfood.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.buyfood.model.User;
import com.buyfood.service.UserMessageService;
import com.buyfood.util.BaseController;
import com.buyfood.util.CommonUtil;
import com.buyfood.util.MD5Util;
import com.buyfood.util.PicUploadUtil;
import com.buyfood.util.StringUtil;

@RequestMapping(value = "user", method = RequestMethod.POST)
@Controller
public class UserController extends BaseController {

	@Autowired
	private UserMessageService messageSevice;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject register(User user, HttpServletRequest request, HttpServletResponse response) {

		if (user.getLoginname() == null) {
			return CommonUtil.constructResponse(0, "账号不为空", null);
		}
		if (user.getLoginpwd() == null) {
			return CommonUtil.constructResponse(0, "密码不为空", null);
		}
		if (user.getName() == null) {
			return CommonUtil.constructResponse(0, "用户名不为空", null);
		}
		if (!StringUtil.checkPhoneNumber(user.getUsertel())) {
			return CommonUtil.constructResponse(0, "手机号码不正确", null);
		}

		String loginpwd = MD5Util.MD5Encode(user.getLoginpwd(), "UTF-8");
		user.setLoginpwd(loginpwd);

		// 获取用户注册时间
		Date date = new Date();
		long time = date.getTime();
		user.setAddtime(time);
		// 插入数据库
		try {
			messageSevice.insertUser(user);
		} catch (Exception e) {
			System.out.println(45645);
			return CommonUtil.constructResponse(-5, "数据库异常", null);
		}
		return CommonUtil.constructResponse(1, "成功", null);

	}

	@RequestMapping(value = "getMessage", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getMessage(HttpServletRequest request) {
		Map<String, Object> message = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			try {
				message = messageSevice.getMessage(user.getId());
			} catch (Exception e) {
				return CommonUtil.constructResponse(-5, "数据库异常", null);
			}
			return CommonUtil.constructResponse(1, "成功", message);
		} else {
			return CommonUtil.constructResponse(0, "用户未登录", message);

		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateMessage(User user, HttpServletRequest request) {
		Map<String, Object> message = new HashMap<String, Object>();
		int id = (int) request.getSession().getAttribute("user");
		user.setId(id);
		try {
			messageSevice.updateMessage(user);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库异常", null);
		}
		return CommonUtil.constructResponse(1, "成功", message);

	}

	@RequestMapping("userpicupload")
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
						&& !fileSuffixName.equals(".jpeg") && !fileSuffixName.equals(".gif")) {
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

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject login(@RequestParam(value = "loginname") String loginname,
			@RequestParam(value = "loginpwd") String loginpwd, HttpServletRequest request) {
		Map<String, Object> message = new HashMap<String, Object>();
		if (loginname == null && loginname == " ") {
			return CommonUtil.constructResponse(-2, "账号为空", null);
		} else if (loginpwd == null && loginpwd == " ") {
			return CommonUtil.constructResponse(-3, "密码有误", null);
		} else {
			String loginpwdM = MD5Util.MD5Encode(loginpwd, "utf-8");
			try {
				message = messageSevice.getLoginpwd(loginname);

				if (message == null) {
					return CommonUtil.constructResponse(-2, "账号不存在", null);
				}
				String temp = (String) message.get("loginpwd");
				message.remove("loginpwd");
				if (temp == null) {
					return CommonUtil.constructResponse(-2, "账号不存在", null);

				} else if (!temp.equals(loginpwdM)) {
					return CommonUtil.constructResponse(-3, "密码错误", null);
				}
			} catch (Exception e) {
				return CommonUtil.constructResponse(-5, "数据库错误", null);
			}
			int id = (int) message.get("id");
			User user = new User();
			System.out.println(user);
			user.setId(id);
			user.setLoginname(loginname);
			System.out.println(user.getId());
			message.remove("id");
			request.getSession().setAttribute("user", user);
			return CommonUtil.constructResponse(1, "登录成功", message);
		}
	}

	@RequestMapping(value = "getUser", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getUser() {
		List<Map<String, Object>> userMessage;
		try {
			userMessage = messageSevice.getUser();

		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}

		return CommonUtil.constructResponse(1, "成功", userMessage);
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteUser(@RequestParam(value = "id", required = true) int id) {
		try {
			messageSevice.deleteUser(id);
		} catch (Exception e) {
			return CommonUtil.constructResponse(-5, "数据库错误", null);
		}
		return CommonUtil.constructResponse(1, "删除成功", null);
	}
}
