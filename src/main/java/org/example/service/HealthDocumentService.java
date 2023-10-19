package org.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.HealthDocument;
import org.example.untils.DTO.Condition;

/**
 * 健康指导服务
 */
public interface HealthDocumentService extends IService<HealthDocument> {

    Page<HealthDocument> selectPage(Condition condition);

    HealthDocument updateHealthDocument(HealthDocument healthDocument);
}
