package com.buyfood.util;

import java.util.Date;

/**
 * 
* @ClassName: DateUtil
* @Description: 时间处理工具
* @author BeiwEi(彭俊豪)
* @date 2017年4月22日 下午9:43:30
*
 */
public class DateUtil {
	//获取当前时间戳
	public static long getTimeStamp(){
		Date date = new Date();
		return date.getTime();
	}
	
}
