package com.sizatn.ssd.dao.secondary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sizatn.ssd.entity.User;

@Mapper
public interface UrMapper {

	List<Map<String, Object>> getUserList();

	int saveUser(@Param(value = "user") User user);

	Map<String, Object> getUser(@Param(value = "userNo") String userNo);

	int updateUser(@Param(value = "user") User user);

	int deleteUser(@Param(value = "userNo") String userNo);

}
