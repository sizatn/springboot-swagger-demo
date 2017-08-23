package com.sizatn.ssd.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sizatn.ssd.dao.secondary.UrMapper;
import com.sizatn.ssd.entity.User;
import com.sizatn.ssd.service.UserService;

@Service("urService")
public class UrServiceImpl implements UserService {

	@Resource(name = "urMapper")
	private UrMapper urMapper;

	@Override
	public PageInfo<Map<String, Object>> getUserList() {
		PageHelper.startPage(1, 2);
		List<Map<String, Object>> list = urMapper.getUserList();
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		return pageInfo;
	}

	@Override
	public int saveUser(User user) {
		return urMapper.saveUser(user);
	}

	@Override
	public Map<String, Object> getUser(String userNo) {
		return urMapper.getUser(userNo);
	}

	@Override
	public int updateUser(User user) {
		return urMapper.updateUser(user);
	}

	@Override
	public int deleteUser(String userNo) {
		return urMapper.deleteUser(userNo);
	}

}
