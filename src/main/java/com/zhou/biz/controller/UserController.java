package com.zhou.biz.controller;

import com.zhou.biz.entity.User;
import com.zhou.biz.service.UserVerifyService;
import com.zhou.util.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.zhou.util.ResultUtil;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@Slf4j
@Api("用户接口")
@RequestMapping(value = "")
public class UserController {
    @Resource
    UserVerifyService userVerifyService;

    @ApiOperation("获取用户信息")
    @GetMapping(value="/userinfo")
    public CommonResponse getUserInfo(@RequestParam( name = "user_name", required = true) String username,
                                      @RequestParam( name = "age",required= false ) Integer age){
        User user = new User();
        user.setAge(20);
        user.setName(username);
        return ResultUtil.success(user);
    }

    @ApiOperation("插入一条用户信息")
    @PostMapping(value="/insert/user")
    public CommonResponse insertOneUser(@RequestBody User user){
        return ResultUtil.success(userVerifyService.addUser(user));
    }

    @ApiOperation("获取所有的用户信息")
    @GetMapping(value="/users")
    public CommonResponse getAllUsers() throws IOException {
        return ResultUtil.success(userVerifyService.findAllUsers());
    }

    @ApiOperation("更新一个用户信息")
    @PostMapping(value="/update/user")
    public CommonResponse updateUser(@RequestBody User user){
        return ResultUtil.success(userVerifyService.updateUser(user));
    }

    @GetMapping(value="/user/detail")
    public CommonResponse getUserDetail(@RequestParam(name="name",required = true) String name){
        return ResultUtil.success(userVerifyService.getOneUser(name));
    }

    @GetMapping(value="clear/cache")
    public CommonResponse clearCache(@RequestParam(name="name",required = true)String name){
        return ResultUtil.success((userVerifyService.clearOneUserCache(name)));
    }

}
