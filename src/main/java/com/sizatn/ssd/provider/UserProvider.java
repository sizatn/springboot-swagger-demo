package com.sizatn.ssd.provider;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.sizatn.ssd.entity.User;

public class UserProvider {

	public String getUser(String userNo) {
		return new SQL() {
			{
				SELECT("*");
				FROM("user");
				WHERE("user_no = " + userNo);
			}
		}.toString();
	}

	public String saveUser(Map<String, Object> param) {
		User user = (User) param.get("user");
		return new SQL() {
			{
				INSERT_INTO("user");
				if (StringUtils.isNotBlank(user.getUserNo())) {
					VALUES("user_no", "#{user.userNo}");
				}
				if (StringUtils.isNotBlank(user.getUserName())) {
					VALUES("user_name", "#{user.userName}");
				}
				if (StringUtils.isNotBlank(user.getPassword())) {
					VALUES("password", "#{user.password}");
				}
				if (StringUtils.isNotBlank(user.getGender())) {
					VALUES("gender", "#{user.gender}");
				}
				if (StringUtils.isNotBlank(user.getPhone())) {
					VALUES("phone", "#{user.phone}");
				}
				if (StringUtils.isNotBlank(user.getMobile())) {
					VALUES("mobile", "#{user.mobile}");
				}
				if (StringUtils.isNotBlank(user.getEnabled())) {
					VALUES("enabled", "#{user.enabled}");
				}
				VALUES("create_date", "NOW()");
				VALUES("update_date", "NOW()");
			}
		}.toString();
	}

	public String updateUser(User user) {
		return new SQL() {
			{
				UPDATE("user");
				if (StringUtils.isNotBlank(user.getUserName())) {
					SET("user_name = #{userName}");
				}
				if (StringUtils.isNotBlank(user.getPassword())) {
					SET("password = #{password}");
				}
				if (StringUtils.isNotBlank(user.getGender())) {
					SET("gender = #{gender}");
				}
				if (StringUtils.isNotBlank(user.getPhone())) {
					SET("phone = #{phone}");
				}
				if (StringUtils.isNotBlank(user.getMobile())) {
					SET("mobile = #{mobile}");
				}
				if (StringUtils.isNotBlank(user.getEnabled())) {
					SET("enabled = #{enabled}");
				}
				SET("update_date = NOW()");
				WHERE("user_no = #{userNo}");
			}
		}.toString();
	}
	
	public String deleteUser() {
		return new SQL() {
			{
				DELETE_FROM("user");
				WHERE("user_no = #{userNo}");
			}
		}.toString();
	}

}
