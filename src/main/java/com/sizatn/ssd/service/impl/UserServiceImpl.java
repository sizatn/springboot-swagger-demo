package com.sizatn.ssd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sizatn.ssd.dao.primary.UserMapper;
import com.sizatn.ssd.entity.User;
import com.sizatn.ssd.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Override
	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public int saveUser(User user) {
		return userMapper.saveUser(user);
	}

	@Override
	public User getUser(String id) {
		return userMapper.getUser(id);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	@Override
	public int deleteUser(String id) {
		return userMapper.deleteUser(id);
	}

}
