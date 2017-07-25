package com.sizatn.ssd.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sizatn.ssd.entity.User;

@Mapper
public interface UserMapper {

	List<User> getUserList();

	int saveUser(User user);

	User getUser(String userNo);

	int updateUser(User user);

	int deleteUser(String userNo);

}
