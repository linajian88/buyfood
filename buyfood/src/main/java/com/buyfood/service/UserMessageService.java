package com.buyfood.service;

import java.util.List;
import java.util.Map;

import com.buyfood.model.User;

public interface UserMessageService {
	// 注册添加信息
	public void insertUser(User user);

	// 登陆验证密码和用户类型
	public Map<String, Object> getLoginpwd(String loginname);

	// 获取用户的基本信息
	public List<Map<String, Object>> getUser();

	// 删除用户
	public void deleteUser(int id);

	// 获得用户信息
	public Map getMessage(int id);

	// 更新用户信息
	public void updateMessage(User user);
}
