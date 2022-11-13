package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author lqq
 * @TableName t_department
 */
@Data
public class Department implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 部门编号
     */
    private String did;

    /**
     * 部门名
     */
    private String departmentName;

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