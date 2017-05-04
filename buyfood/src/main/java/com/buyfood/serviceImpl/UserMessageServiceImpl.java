package com.buyfood.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buyfood.dao.UserMessageDao;
import com.buyfood.model.User;
import com.buyfood.service.UserMessageService;

@Service("userMessage")
public class UserMessageServiceImpl implements UserMessageService {

	@Autowired
	private UserMessageDao userMessagedao;

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		System.out.println(user);
		userMessagedao.insertUser(user);
	}

	@Override
	public Map<String, Object> getLoginpwd(String loginname) {

		Map<String, Object> loginpwd = userMessagedao.getLoginpwd(loginname);
		return loginpwd;
	}

	@Override
	public List<Map<String, Object>> getUser() {
		List<Map<String, Object>> userMessage = userMessagedao.getUser();

		return userMessage;
	}

	@Override
	public void deleteUser(int id) {
		userMessagedao.deleteUser(id);

	}

	@Override
	public Map getMessage(int id) {
		Map<String, Object> userMessage = userMessagedao.getMessage(id);

		return userMessage;
	}

	@Override
	public void updateMessage(User user) {

		userMessagedao.updateMessage(user);

	}
}
