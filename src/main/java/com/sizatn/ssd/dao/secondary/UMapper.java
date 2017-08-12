package com.sizatn.ssd.dao.secondary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sizatn.ssd.entity.User;

@Mapper
public interface UMapper {

//	@Select("SELECT * FROM user")
//	@Results({ @Result(property = "id", column = "id"), 
//		@Result(property = "userNo", column = "user_no"),
//		@Result(property = "userName", column = "user_name"),
//		@Result(property = "password", column = "password"),
//		@Result(property = "gender", column = "gender"),
//		@Result(property = "phone", column = "phone"),
//		@Result(property = "mobile", column = "mobile"),
//		@Result(property = "enabled", column = "enabled"),
//		@Result(property = "createDate", column = "create_date"),
//		@Result(property = "updateDate", column = "update_date")})
//	List<Map<String, Object>> getUserList();
//
//	int saveUser(@Param(value = "user") User user);
//
//	@Select("SELECT * FROM user WHERE user_no = #{userNo}")
//	@Results({ @Result(property = "id", column = "id"), 
//		@Result(property = "userNo", column = "user_no"),
//		@Result(property = "userName", column = "user_name"),
//		@Result(property = "password", column = "password"),
//		@Result(property = "gender", column = "gender"),
//		@Result(property = "phone", column = "phone"),
//		@Result(property = "mobile", column = "mobile"),
//		@Result(property = "enabled", column = "enabled"),
//		@Result(property = "createDate", column = "create_date"),
//		@Result(property = "updateDate", column = "update_date")})
//	Map<String, Object> getUser(@Param(value = "userNo") String userNo);
//
//	int updateUser(@Param(value = "user") User user);
//
//	int deleteUser(@Param(value = "userNo") String userNo);

}
