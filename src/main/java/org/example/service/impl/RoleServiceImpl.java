package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.RoleMapper;
import org.example.entity.Role;
import org.example.service.RoleService;
import org.example.untils.DTO.Condition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Resource
    private RoleMapper roleMapper;

    /**
     * 选择所有角色功能的具体实现
     *
     * @return
     */
    @Override
    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectList(null);
        return roles;
    }

    /**
     * 分页功能的具体实现
     *
     * @param condition
     * @return
     */
    @Override
    public Page selectPage(Condition condition) {
        Page<Role> rolePage = new Page<>(condition.getPageNum(), condition.getPageSize());
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        String column = condition.getClauses().get(0).getColumn();
        String value = (String) condition.getClauses().get(0).getValue();
        //根据角色备注remark是否存在 分页查询
        roleLambdaQueryWrapper.like(column != null, Role::getRemark, value)
                .orderByDesc(Role::getUpdateDatetime);
        Page<Role> page = roleMapper.selectPage(rolePage, roleLambdaQueryWrapper);
        return page;
    }

}
