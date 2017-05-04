package com.buyfood.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName: PicUploadUtil
 * @Description: youdiankun
 * @author BeiwEi(PENGJUNHAO)
 * @date 2017年4月24日 下午8:32:25
 *
 */
public class PicUploadUtil {

	/**
	 * @Title: PicUpload
	 * @Description: 文件上传工具
	 * @return Map<Integer,String> 返回状态码：'-1'———出错 '1'————成功 和 具体信息
	 */
	public static Map<Integer, String> PicUpload(HttpServletRequest request, String path) {

		Map<Integer, String> result = new HashMap<Integer, String>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断request 是否有文件上传,即多部分请求
		if (cmr.isMultipart(request)) {
			// 转换成多部分解析器
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			// 取得request中所有文件名
			Iterator<String> iter = mhsr.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile mfile = mhsr.getFile((String) iter.next());
				// 取得当前上传文件的文件名称
				String filename = mfile.getOriginalFilename();
				// 取得文件名后缀
				String fileSuffixName = filename.substring(filename.lastIndexOf("."));

				// 判断文件大小，设置文件上传大小限制
				if (mfile.getSize() > 1048576) {
					result.put(-1, "文件大小超过1M，上传失败");
					return result;
				}

				// 判断文件格式，这里判断文件后缀是否是图片
				if (!fileSuffixName.equals(".jpg") && !fileSuffixName.equals(".png") && !fileSuffixName.equals(".JPG")
						&& !fileSuffixName.equals(".JPEG") && !fileSuffixName.equals(".jpeg")
						&& !fileSuffixName.equals(".gif")) {
					result.put(-1, "文件不是图片格式");
					return result;
				}

				// 生成的GUID为一串32位字符组成的128位数据上传文件重命名newFileName
				String UUID = CommonUtil.GUID();

				String newFileName = UUID + fileSuffixName;

				// 生成本地文件存储路径
				String rp = request.getSession().getServletContext().getRealPath("/");

				String FilePath = rp + "/" + newFileName;

				File file = new File(FilePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				// 将上传文件传到本地file中
				try {
					mfile.transferTo(file);
					result.put(1, newFileName);
					return result;
				} catch (IllegalStateException | IOException e) {
					result.put(-1, "文件存入磁盘失败");
					return result;
				}
			}
		}
		result.put(-1, "没有文件上传");
		return result;
	}

	public static String userpicUpload(HttpServletRequest request, HttpServletResponse response) {
		String result = null;// 上传后返回情况说明
		String path = null;// 上传图片路径
		// //创建一个通用的多部分解析器
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		System.out.println("czxczx"+cmr.isMultipart(request));
		if (cmr.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			// //取得request中的所有文件名
			Iterator<String> iter = mhsr.getFileNames();
			System.out.println(iter.hasNext());
			while (iter.hasNext()) {
				// //取得上传文件
				MultipartFile file = mhsr.getFile((String) iter.next());

				// //取得当前上传文件的文件名称
				String filename = file.getOriginalFilename();
				// 获得文件后缀
				String fileSuffixName = filename.substring(filename.indexOf("."));
				/**
				 * 上传文件大小,类型判断
				 */
				if (file.getSize() > 1048576) {
					result = "上传失败：上传文件大小大于1M";
					return null;

				} else if (!fileSuffixName.equals(".jpg") && !fileSuffixName.equals(".png")
						&& !fileSuffixName.equals(".JPG") && !fileSuffixName.equals(".JPEG")
						&& !fileSuffixName.equals(".jpeg") && !fileSuffixName.equals(".gif")) {
					result = "上传失败：上传文件类型不正确";
					return null;
				}

				// 生成的GUID为一串32位字符组成的128位数据上传文件重命名filename1
				CommonUtil cu = new CommonUtil();
				String UUID = cu.GUID();

				String filename1 = UUID + fileSuffixName;
				path = request.getSession().getServletContext().getRealPath("/image");
				File file3 = new File(path + "/" + filename1);
				if (!file3.exists()) {
					file3.mkdir();
				}
				// String snacks_pic1 = filename1;
				try {
					// transfer方法是MultipartFile包中提供的方法，直接可以写入文件到指定目录
					file.transferTo(file3);
					JSONObject jo = new JSONObject();
					jo.put("pic", path + "/" + filename1);
					System.out.println(jo);
					return "../image/" + filename1;
				} catch (Exception e) {
					result = e.getMessage();
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
}
