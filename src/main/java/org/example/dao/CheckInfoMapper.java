package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.CheckInfo;
import java.util.List;

/**
 健康疾病 （具体类型）
 */
public interface CheckInfoMapper extends BaseMapper<CheckInfo> {
    List<CheckInfo> getDataAnalysis(@Param("userId") Integer userId);

    CheckInfo getCurrentCheckInfo(@Param("userId") Integer userId);
}
