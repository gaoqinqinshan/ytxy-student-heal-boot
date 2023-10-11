package org.example.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.example.dao.UserMapper;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Security;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * 服务实现类
 */
@Slf4j
@Service
public class UserServeImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 登录的具体实现
     */
    @Override
    public User login(User user) {
        String userName = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User loginUser = (User) subject.getPrincipal();
            if (loginUser != null) {
                return loginUser;
            }
        } catch (AuthenticationException e) {
            log.info("登陆失败：" + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public User selectByKey(Integer userId) {
        return null;
    }


    @Override
    public List<User> selectUserByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public boolean saveByuserName(User user) {
        return false;
    }

    @Override
    public Page<User> selectPage(Condition condition) {
        return null;
    }

    @Override
    public User updateById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<User> update(User user) {
        return null;
    }
}
