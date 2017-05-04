package com.buyfood.dao;

import java.util.List;
import java.util.Map;

import com.buyfood.model.User;

public interface UserMessageDao {

	public void insertUser(User user);

	public Map<String, Object> getLoginpwd(String loginname);

	public List<Map<String, Object>> getUser();

	public void deleteUser(int id);

	public Map<String, Object> getMessage(int id);

	public void updateMessage(User user);
}
