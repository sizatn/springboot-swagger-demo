package com.sizatn.ssd.dao.primary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.cache.annotation.CachePut;

import com.sizatn.ssd.entity.User;
import com.sizatn.ssd.provider.UserProvider;

public interface UserMapper {

	@CachePut(value = "users")
	@Select("SELECT * FROM user")
	@Results({ @Result(property = "id", column = "id"), 
		@Result(property = "userNo", column = "user_no"),
		@Result(property = "userName", column = "user_name"),
		@Result(property = "password", column = "password"),
		@Result(property = "gender", column = "gender"),
		@Result(property = "phone", column = "phone"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "enabled", column = "enabled"),
		@Result(property = "createDate", column = "create_date"),
		@Result(property = "updateDate", column = "update_date")})
	List<Map<String, Object>> getUserList();

	@SelectProvider(type = UserProvider.class,  method = "getUser")
	@Results({ @Result(property = "id", column = "id"), 
		@Result(property = "userNo", column = "user_no"),
		@Result(property = "userName", column = "user_name"),
		@Result(property = "password", column = "password"),
		@Result(property = "gender", column = "gender"),
		@Result(property = "phone", column = "phone"),
		@Result(property = "mobile", column = "mobile"),
		@Result(property = "enabled", column = "enabled"),
		@Result(property = "createDate", column = "create_date"),
		@Result(property = "updateDate", column = "update_date")})
	Map<String, Object> getUser(String userNo);
	
	@InsertProvider(type = UserProvider.class, method = "saveUser")
	@Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "user.id")
	int saveUser(@Param(value = "user") User user);

	@UpdateProvider(type = UserProvider.class, method = "updateUser")
	int updateUser(User user);

	@UpdateProvider(type = UserProvider.class, method = "deleteUser")
	int deleteUser(@Param(value = "userNo") String userNo);

}
