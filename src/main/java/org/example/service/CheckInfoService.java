package org.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import org.example.entity.CheckInfo;
import org.example.untils.DTO.Condition;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 健康疾病 （具体类型）
 */
public interface CheckInfoService extends IService<CheckInfo> {

    ResponseEntity<CheckInfo> getCurrentCheckInfo(Integer userId);

    List<CheckInfo> getDataAnalysis(Integer userId);

    Page<CheckInfo> selectPage(Condition condition);

    boolean CheckIsExist(Integer userId, String checkYear);
}
