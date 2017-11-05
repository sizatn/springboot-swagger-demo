package com.sizatn.ssd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @desc user model
 * @author sizatn
 * @date Jun 10, 2017
 */
@ApiModel(value = "User", description = "用户对象")
public class User implements Serializable {

	private static final long serialVersionUID = -1666005991691679694L;
	
	private int id;
	private String userNo;
	private String userName;
	private String password;
	
	/** male:"0" female:"1" */
	private String gender;
	private String phone;
	private String mobile;
	
	/** yes:"0" no:"1" */
	private String enabled;
	private Timestamp createDate;
	private Timestamp updateDate;

	public User() {
		
	}

	public User(int id, String userNo, String userName, String password, String gender, String phone, String mobile,
			String enabled, Timestamp createDate, Timestamp updateDate) {
		this.id = id;
		this.userNo = userNo;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.mobile = mobile;
		this.enabled = enabled;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	@JsonProperty(value = "id")
	@ApiModelProperty(value = "编号")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty(value = "user_no")
	@ApiModelProperty(value = "用户编号")
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@JsonProperty(value = "user_name")
	@ApiModelProperty(value = "用户名称")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty(value = "password")
	@ApiModelProperty(value = "用户密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty(value = "enabled")
	@ApiModelProperty(value = "是否启用")
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@JsonProperty(value = "phone")
	@ApiModelProperty(value = "电话")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty(value = "mobile")
	@ApiModelProperty(value = "移动电话")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@JsonProperty(value = "gender")
	@ApiModelProperty(value = "性别")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "create_date")
	@ApiModelProperty(value = "创建时间")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "update_date")
	@ApiModelProperty(value = "更新时间")
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}