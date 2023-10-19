package org.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.example.entity.User;
import org.example.untils.DTO.Condition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 用户登录
     */
    User login(User user);

    /**
     * 通过key选择用户
     */
    User selectByKey(Integer userId);

    /**
     * 通过角色id选择
     */
    List<User> selectUserByRoleId(Integer roleId);

    /**
     *
     */
    boolean saveByuserName(User user);

    /**
     * 页数
     */
    Page<User> selectPage(Condition condition);

    User updateById(Integer id);
    ResponseEntity<User> update(User user);
}
