package com.sizatn.springbootmybatis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sizatn.springbootmybatis.dao.UserMapper;
import com.sizatn.springbootmybatis.entity.User;
import com.sizatn.springbootmybatis.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userMapper")
	private UserMapper userMapper;

	@Override
	public PageInfo<Map<String, Object>> getUserList() {
		PageHelper.startPage(1, 2);
		List<Map<String, Object>> list = userMapper.getUserList();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		return pageInfo;
	}

	@Override
	public int saveUser(User user) {
		return userMapper.saveUser(user);
	}

	@Override
	public Map<String, Object> getUser(String userNo) {
		return userMapper.getUser(userNo);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(String userNo) {
		return userMapper.deleteUser(userNo);
	}

}
