package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author lqq
 * @TableName t_user
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private String uid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别: 保密(s), 男(m), 女(f)
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 入职时间
     */
    private Date induction;

    /**
     * 部门（使用部门编号）
     */
    private String department;

    /**
     * 职位（使用职位编号）
     */
    private String role;

    /**
     * 是否已婚
     */
    private Integer isMarried;

    /**
     * 简历
     */
    private String resume;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标识
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}