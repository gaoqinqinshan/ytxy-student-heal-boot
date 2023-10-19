package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Resources;
import org.example.untils.Vo.ResourceVO;
import java.util.List;

/**
 *
 */
public interface ResourcesServe extends IService<Resources> {
    List<ResourceVO> getResourceTreeByRoleId(Integer roleId, Integer typeId);

    List<ResourceVO> getResourceTreeByUserId(Integer roleId, Integer typeId);
}
