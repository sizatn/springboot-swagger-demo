package com.sizatn.ssd.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sizatn.ssd.entity.User;

@Mapper
public interface UserMapper {

	List<User> getUserList();

	int saveUser(@Param(value = "user") User user);

	User getUser(@Param(value = "userNo") String userNo);

	int updateUser(@Param(value = "user") User user);

	int deleteUser(@Param(value = "userNo") String userNo);

}
