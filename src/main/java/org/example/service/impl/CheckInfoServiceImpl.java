package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.advice.ExceptionEnums;
import org.example.advice.MyException;
import org.example.dao.CheckInfoMapper;
import org.example.entity.CheckInfo;
import org.example.service.CheckInfoService;
import org.example.untils.DTO.Clause;
import org.example.untils.DTO.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-12
 */
@Slf4j
@Service
public class CheckInfoServiceImpl extends ServiceImpl<CheckInfoMapper, CheckInfo> implements CheckInfoService {

    @Resource
    private CheckInfoMapper checkInfoMapper;

    @Override
    public ResponseEntity<CheckInfo> getCurrentCheckInfo(Integer userId) {
        CheckInfo checkInfo = checkInfoMapper.getCurrentCheckInfo(userId);
        //System.out.println("======================="+checkInfo);
        if (checkInfo == null) {
            throw new MyException(ExceptionEnums.NO_CHECK_INFO);
        } else {
            return ResponseEntity.ok(checkInfo);
        }
    }

    @Override
    public List<CheckInfo> getDataAnalysis(Integer userId) {
        List<CheckInfo> dataAnalysis = checkInfoMapper.getDataAnalysis(userId);
        return dataAnalysis;
    }

    @Override
    public Page<CheckInfo> selectPage(Condition condition) {
        Page<CheckInfo> wpage = new Page<>(condition.getPageNum(), condition.getPageSize());
        LambdaQueryWrapper<CheckInfo> checkInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //条件分页查询 使用queryWrapper
        //.like()
        for (Clause clause : condition.getClauses()) {
            if ("userId".equals(clause.getColumn())){
                checkInfoLambdaQueryWrapper.eq(clause.getValue() != null, CheckInfo::getUserId, clause.getValue());
            }else if ("college".equals(clause.getColumn())){
                checkInfoLambdaQueryWrapper.like(clause.getValue() != null, CheckInfo::getCollege, clause.getValue());
            } else if ("major".equals(clause.getColumn())){
                checkInfoLambdaQueryWrapper.like(clause.getValue() != null, CheckInfo::getMajor, clause.getValue());
            } else if ("name".equals(clause.getColumn())){
                checkInfoLambdaQueryWrapper.like(clause.getValue() != null, CheckInfo::getName, clause.getValue());
            }else if ("checkYear".equals(clause.getColumn())){
                checkInfoLambdaQueryWrapper.like(clause.getValue() != null, CheckInfo::getCheckYear, clause.getValue());
            }
        }
        checkInfoLambdaQueryWrapper.orderByDesc(CheckInfo::getUpdateDatetime);
        Page<CheckInfo> page = checkInfoMapper.selectPage(wpage, checkInfoLambdaQueryWrapper);
        log.info("分页数据=》》》》》》{}", page.getRecords());
        return page;
    }

    @Override
    public boolean CheckIsExist(Integer userId, String checkYear) {
        LambdaQueryWrapper<CheckInfo> checkInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        checkInfoLambdaQueryWrapper.eq(CheckInfo::getUserId, userId)
                .eq(CheckInfo::getCheckYear, checkYear);
        CheckInfo checkInfo = checkInfoMapper.selectOne(checkInfoLambdaQueryWrapper);
        //log.info("当前学生检查信息{}",checkInfo);
        //检查信息不存在时， 则可以添加
        return checkInfo == null;
    }
}
