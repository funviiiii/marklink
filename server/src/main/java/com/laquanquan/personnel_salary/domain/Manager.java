package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author lqq
 * @TableName t_manager
 */
@Data
public class Manager implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private String uid;

    /**
     * 口令
     */
    private String auth;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标识: 已删除(1), 未删除(0)
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}