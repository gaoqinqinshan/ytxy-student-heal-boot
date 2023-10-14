package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.entity.HealthDocument;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-10-12
 */
public interface HealthDocumentMapper extends BaseMapper<HealthDocument> {
    IPage<HealthDocument> selectPageBy(Page<HealthDocument> page,
                                       String content,
                                       String author,
                                       String book,
                                       int isPublished);
}
