package com.sizatn.ssd.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sizatn.ssd.entity.User;

public interface UserService {

	PageInfo<Map<String, Object>> getUserList();

	int saveUser(User user);

	List<Map<String, Object>> getUser(String userNo);

	int updateUser(User user);

	int deleteUser(String userNo);

}
