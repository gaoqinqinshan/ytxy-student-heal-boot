package org.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Role;
import org.example.untils.DTO.Condition;

import java.awt.*;
import java.util.Collection;
import java.util.List;

/**
 * 角色功能
 */
public interface RoleService extends IService<Role> {
    /**
     * 选择所有的角色
     *
     * @return
     */
    List<Role> selectAll();

    /**
     * 分页
     */
    Page selectPage(Condition condition);
}
