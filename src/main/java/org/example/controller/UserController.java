package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.untils.UserThreadLocal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 登录
     */
    @ApiOperation(value = "用户登录接口")
    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loginUser = userService.login(user);
        //log.info("用户{}",user);
        UserThreadLocal.put(loginUser);
        return ResponseEntity.ok(loginUser);
    }

    /**
     * 登录错误
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.status(301).body("登录过期或者未登录,请退出重新登录");
    }
}
