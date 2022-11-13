package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author lqq
 * @TableName t_log
 */
@Data
public class Log implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 日志编号
     */
    private String lid;

    /**
     * 操作用户
     */
    private String user;

    /**
     * 操作详细记录
     */
    private String msg;

    /**
     * 操作行为级别
     */
    private Integer level;

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