package com.sizatn.springbootmybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sizatn.springbootmybatis.entity.User;

public interface UserMapper {

	@Select("SELECT * FROM user")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "userNo", column = "user_no"),
			@Result(property = "userName", column = "user_name"), @Result(property = "password", column = "password"),
			@Result(property = "gender", column = "gender"), @Result(property = "phone", column = "phone"),
			@Result(property = "mobile", column = "mobile"), @Result(property = "enabled", column = "enabled"),
			@Result(property = "createDate", column = "create_date"),
			@Result(property = "updateDate", column = "update_date") })
	List<Map<String, Object>> getUserList();

	@Select("SELECT * FROM user WHERE user_no = #{userNo}")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "userNo", column = "user_no"),
			@Result(property = "userName", column = "user_name"), @Result(property = "password", column = "password"),
			@Result(property = "gender", column = "gender"), @Result(property = "phone", column = "phone"),
			@Result(property = "mobile", column = "mobile"), @Result(property = "enabled", column = "enabled"),
			@Result(property = "createDate", column = "create_date"),
			@Result(property = "updateDate", column = "update_date") })
	Map<String, Object> getUser(String userNo);

	@Insert("INSERT INTO user (user_no, user_name, password, gender, phone, mobile, enabled, create_date, update_date) "
			+ "VALUES (#{user.userNo}, #{user.userName}, #{user.password}, #{user.gender}, #{user.phone}, #{user.mobile}, #{user.enabled}, NOW(), NOW())")
	@Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "user.id")
	int saveUser(@Param(value = "user") User user);

	@Update("UPDATE user SET user_name = #{user.userName,jdbcType=VARCHAR}, password = #{user.password,jdbcType=VARCHAR}, gender = #{user.gender,jdbcType=VARCHAR}, "
			+ "phone = #{user.phone,jdbcType=VARCHAR}, mobile = #{user.mobile,jdbcType=VARCHAR}, enabled = #{user.enabled,jdbcType=VARCHAR}, update_date = NOW() WHERE user_no = #{user.userNo,jdbcType=VARCHAR}")
	int updateUser(@Param(value = "user") User user);

	@Delete("DELETE FROM user WHERE user_no = #{userNo}")
	int deleteUser(String userNo);

}
