package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author lqq
 * @TableName t_role
 */
@Data
public class Role implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 职位编号
     */
    private String rid;

    /**
     * 职称
     */
    private String roleName;

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