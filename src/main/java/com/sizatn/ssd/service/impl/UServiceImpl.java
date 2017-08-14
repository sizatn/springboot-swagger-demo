package com.sizatn.ssd.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sizatn.ssd.dao.secondary.UMapper;
import com.sizatn.ssd.entity.User;
import com.sizatn.ssd.service.UserService;

@Service("uService")
public class UServiceImpl implements UserService {

	@Resource(name = "uMapper")
	private UMapper uMapper;

	@Override
	public PageInfo<Map<String, Object>> getUserList() {
		PageHelper.startPage(1, 2);
		List<Map<String, Object>> list = uMapper.getUserList();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		return pageInfo;
	}

	@Override
	public int saveUser(User user) {
		return uMapper.saveUser(user);
	}

	@Override
	public Map<String, Object> getUser(String userNo) {
		return uMapper.getUser(userNo);
	}

	@Override
	public int updateUser(User user) {
		return uMapper.updateUser(user);
	}

	@Override
	public int deleteUser(String userNo) {
		return uMapper.deleteUser(userNo);
	}

}
