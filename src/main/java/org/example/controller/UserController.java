package org.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.example.advice.ExceptionEnums;
import org.example.advice.MyException;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.untils.DTO.Condition;
import org.example.untils.MD5Utils;
import org.example.untils.UserThreadLocal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
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
     * 登录错误(登录过期或者未登录,请退出重新登录)
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/login")
    public ResponseEntity login() {
        return ResponseEntity.status(301).body("登录过期或者未登录,请退出重新登录");
    }

    /**
     * 查询所有的用户
     */
    @ApiOperation(value = "基础接口，返回all数据")
    @GetMapping(value = "all")
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userService.list());
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ApiOperation(value = "用户注销接口")
    @GetMapping("/loginOut")
    public ResponseEntity<String> loginout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        UserThreadLocal.remove();
        return ResponseEntity.ok("退出登录");
    }

    /**
     * 返回指定ID的数据
     * @param id
     * @return
     */
    @ApiOperation(value = "基础接口: 返回指定ID的数据")
    @GetMapping(value = "get/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Integer id) {
        User user = userService.selectByKey(id);
        return ResponseEntity.ok(user);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户 ")
    @PostMapping("/add")
    public ResponseEntity<User> save(@RequestBody User user) {
        boolean b = userService.saveByuserName(user);
        if (b) {
            return ResponseEntity.ok(user);
        }
        throw new MyException(ExceptionEnums.ADD_ERROR);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "用户修改数据 ")
    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        user.setUpdateDatetime(new Date());
        user.setCreateDatetime(new Date());
        if (!userService.updateById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        }
        return userService.update(user);
    }

    /**
     * 获取所有的相应的角色
     *
     * @param roleId
     * @return
     */
    @ApiOperation(value = "获取所有的相应角色用户")
    @GetMapping("/getAllStudent/{roleId}")
    public ResponseEntity<List<User>> getAllStudent(@PathVariable("roleId") Integer roleId) {
        List<User> users = userService.selectUserByRoleId(roleId);
        return ResponseEntity.ok(users);
    }

    /**
     * 分页返回数据
     *
     * @param condition
     * @return
     */
    @ApiOperation(value = "分页返回数据")
    @PostMapping("/page")
    public ResponseEntity<Page<User>> page(@RequestBody Condition condition) {
        return ResponseEntity.ok(userService.selectPage(condition));
    }

    /**
     * 根据id 删除相应的数据
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除相应的数据")
    @GetMapping(value = "delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        boolean b = userService.removeById(id);
        if (b) {
            return ResponseEntity.ok("删除成功");
        }
        throw new MyException(ExceptionEnums.DELETE_ERROR);
    }

    /**
     * 根据用户名字来更新数据
     *
     * @param user
     * @return
     */
    @GetMapping("/updateUser")
    public ResponseEntity<User> updateUserName(@RequestBody User user) {
        ResponseEntity<User> update = this.update(user);
        return update;
    }
}
