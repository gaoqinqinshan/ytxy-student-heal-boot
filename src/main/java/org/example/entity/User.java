package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实例化
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;


    /**
     * 出生
     */
    private Date birth;

    /**
     * 大学
     */
    private String college;

    /**
     * 年级
     */
    private String grade;

    /**
     * 主要
     */
    private String major;


    /**
     * name password
     */
    private String name;
    private String password;

    /**
     * 角色的id 性别
     */
    private Integer roleId;
    private String sex;
    private String stuNo;
    private String username;

    /**
     * 文化水平
     */
    private String cultureLevel;

    /**
     * 国家
     */
    private String nation;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 目前居住地
     */
    private String presentAddress;

    /**
     * 工作的地方
     */
    private String workPlace;

    /**
     * 照片
     */
    private String photo;

    /**
     * 真实年龄
     */
    private String realAge;

    /**
     * 老师的id
     */
    private String teacherId;
}
