package com.sizatn.ssd.service;

import java.util.List;

import com.sizatn.ssd.entity.User;

public interface UserService {

	List<User> getUserList();

	int saveUser(User user);

	User getUser(String userNo);

	int updateUser(User user);

	int deleteUser(String userNo);

}
