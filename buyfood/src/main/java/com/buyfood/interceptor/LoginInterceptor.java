package com.buyfood.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.buyfood.model.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		/* arg1.sendRedirect("/login.html"); */

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		if (user == null && !(request.getRequestURI().contains("/user/login"))
				&& !(request.getRequestURI().contains("/user/userpicupload"))
				&& !(request.getRequestURI().contains("/user/register"))
				&& !(request.getRequestURI().contains("/Food/getFoodByPage"))) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();

			out.write("{\"code\": -1,\"msg\":\"用户未登录\",\"data\": \"\"}");
			// String path =
			// arg0.getSession().getServletContext().getRealPath("/htmls");
			// arg1.sendRedirect(path+"/"+"login.html");
			return false;

		}
		return true;
	}

}
