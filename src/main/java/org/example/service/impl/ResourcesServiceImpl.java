package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ResourcesMapper;
import org.example.entity.Resources;
import org.example.entity.User;
import org.example.service.ResourcesServe;
import org.example.service.UserService;
import org.example.untils.Vo.ResourceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 和前端交互的实现类
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesServe {

    @Resource
    private UserService userService;
    @Resource
    private ResourcesMapper resourcesMapper;


    @Override
    public List<ResourceVO> getResourceTreeByRoleId(Integer roleId, Integer typeId) {
        List<Resources> allNode = resourcesMapper.findRoleResource(roleId, typeId);
        List<ResourceVO> all = new ArrayList<>();
        allNode.forEach(node -> {
            ResourceVO resourceVO = new ResourceVO();
            BeanUtils.copyProperties(node, resourceVO, "children");
            List<ResourceVO> list = new ArrayList<>();
            //System.out.println("list=====================>" + list);
            resourceVO.setChildren(list);
            all.add(resourceVO);
        });
        //System.out.println("all=====================>" + all);
        //筛选出父节点==》parentId==0的节点
        List<ResourceVO> first = all.stream().filter(v -> v.getParentId() == 0).collect(Collectors.toList());

        first.forEach(v -> getTree(v, all));
        return first;
    }

    @Override
    public List<ResourceVO> getResourceTreeByUserId(Integer userId, Integer typeId) {
        User user = userService.selectByKey(userId);
        if (user.getRoleId() != null) {
            return getResourceTreeByRoleId(user.getRoleId(), typeId);
        }
        return null;
    }

    private ResourceVO getTree(ResourceVO resourceVO, List<ResourceVO> all) {
        all.forEach(resource -> {
            //子节点的父节点 和 父节点的id相等，递归调用
            if (resource.getParentId().equals(resourceVO.getId())) {
                resourceVO.getChildren().add(getTree(resource, all));
            }
        });
        return resourceVO;
    }
}
