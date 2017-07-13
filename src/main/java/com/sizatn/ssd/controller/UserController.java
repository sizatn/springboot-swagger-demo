package com.sizatn.ssd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sizatn.ssd.entity.User;
import com.sizatn.ssd.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户APIs")
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Resource(name = "userService")
	private UserService userService;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        if (userService.saveUser(user) != 1) {
        	return "fail";
		}
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "String")
    @RequestMapping(value="/{userNo}", method=RequestMethod.GET)
    public User getUser(@PathVariable String userNo) {
        return userService.getUser(userNo);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.PUT)
    public String putUser(@RequestBody User user) {
        if (userService.updateUser(user) != 1) {
        	return "fail";
		}
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "String")
    @RequestMapping(value="/{userNo}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable String userNo) {
        if (userService.deleteUser(userNo) != 1) {
        	return "fail";
		}
        return "success";
    }

}
