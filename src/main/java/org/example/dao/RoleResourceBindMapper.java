package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.example.entity.RoleResourceBind;

import java.util.List;

/**
 * 用户资源绑定的接口
 */
public interface RoleResourceBindMapper extends BaseMapper<RoleResourceBind> {

    /**
     * 添加绑定资源的接口
     */
    Integer addBindInfo(@Param("resourceId") Integer resourceId, @Param("roleList") List<Integer> roleList);

    /**
     *删除该接口绑定的所有信息
     */
    Integer deleteBindInfo(@Param("resourceId")Integer resourceId);
}
