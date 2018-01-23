package com.sizatn.springbootmybatis.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.sizatn.springbootmybatis.entity.User;
import com.sizatn.springbootmybatis.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理APIs")
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	@ApiOperation(value = "获取用户列表", notes = "")
	@GetMapping(value = "")
	public PageInfo<Map<String, Object>> getUserList() {
		return userService.getUserList();
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户对象", required = true, dataType = "User")
	@PostMapping(value = "")
	public String postUser(@RequestBody User user) {
		if (userService.saveUser(user) != 1) {
			return "fail";
		}
		return "success";
	}

	@ApiOperation(value = "获取用户详细信息", notes = "根据url的userNo来获取用户详细信息")
	@ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "String")
	@GetMapping(value = "/{userNo}")
	public Map<String, Object> getUser(@PathVariable String userNo) {
		return userService.getUser(userNo);
	}

	@ApiOperation(value = "更新用户详细信息", notes = "根据User对象更新用户详细信息")
	@ApiImplicitParam(name = "user", value = "用户对象", required = true, dataType = "User")
	@PutMapping(value = "")
	public String putUser(@RequestBody User user) {
		if (userService.updateUser(user) != 1) {
			return "fail";
		}
		return "success";
	}

	@ApiOperation(value = "删除用户", notes = "根据url的userNo来指定删除对象")
	@ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "String")
	@DeleteMapping(value = "/{userNo}")
	public String deleteUser(@PathVariable String userNo) {
		if (userService.deleteUser(userNo) != 1) {
			return "fail";
		}
		return "success";
	}

}
