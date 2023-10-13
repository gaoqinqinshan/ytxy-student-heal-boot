package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.RoleResourceBindMapper;
import org.example.entity.RoleResourceBind;
import org.example.service.RoleResourceBindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleResourceBindServiceImpl extends ServiceImpl<RoleResourceBindMapper, RoleResourceBind> implements
        RoleResourceBindService {

    @Resource
    private RoleResourceBindMapper roleResourceBindMapper;

    /**
     * 获取角色的id
     */
    @Override
    public List<Integer> getRoleIds(Integer resourceId) {
        LambdaQueryWrapper<RoleResourceBind> roleResourceBindLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleResourceBindLambdaQueryWrapper.eq(RoleResourceBind::getResourceId, resourceId);
        List<RoleResourceBind> list = roleResourceBindMapper.selectList(roleResourceBindLambdaQueryWrapper);
        List<Integer> collect = list.stream().map(RoleResourceBind::getRoleId).collect(Collectors.toList());
        return collect;

    }

    /**
     * 添加角色相应的绑定
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addBindInfo(Integer resourceId, List<Integer> roleList) {
        return roleResourceBindMapper.addBindInfo(resourceId, roleList);
    }

    /**
     * 删除角色相应的绑定
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteBindInfo(Integer resourceId) {
        return roleResourceBindMapper.deleteBindInfo(resourceId);
    }

}
