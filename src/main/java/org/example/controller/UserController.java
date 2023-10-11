package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.untils.UserThreadLocal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation(value = "用户登录接口")
    @RequestMapping("login")
    public ResponseEntity<User> login(@RequestBody User user){
        User loginUser = userService.login(user);
        UserThreadLocal.put(loginUser);
        return ResponseEntity.ok(loginUser);
    }
}
