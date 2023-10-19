package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Resources;

import java.util.List;

public interface ResourcesMapper extends BaseMapper<Resources> {

    List<Resources> findRoleResource(@Param("roleId") Integer roleId, @Param("typeId") Integer typeId);

    /**
     * 获取url对应的权限
     */

    List<Resources>listUrlAndPermission();
}
