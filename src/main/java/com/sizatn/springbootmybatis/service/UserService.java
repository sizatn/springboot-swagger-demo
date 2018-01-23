package com.sizatn.springbootmybatis.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sizatn.springbootmybatis.entity.User;

public interface UserService {

	PageInfo<Map<String, Object>> getUserList();

	int saveUser(User user);

	Map<String, Object> getUser(String userNo);

	int updateUser(User user);

	int deleteUser(String userNo);

}
