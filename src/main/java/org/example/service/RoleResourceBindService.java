package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.RoleResourceBind;
import java.util.List;

public interface RoleResourceBindService extends IService<RoleResourceBind> {

    List<Integer> getRoleIds(Integer resourceId);

    /**
     * 添加绑定信息
     */
    Integer addBindInfo(Integer id, List<Integer> list);

    /**
     * 删除绑定信息
     *
     * @param resourceId
     * @return
     */
    Integer deleteBindInfo(Integer resourceId);
}
