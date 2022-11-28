package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Long id;

    /**
     * 日志编号
     */
    @JsonProperty
    private String lid;

    /**
     * 操作用户
     */
    @JsonProperty
    private String user;

    /**
     * 操作详细记录
     */
    @JsonProperty
    private String msg;

    /**
     * 操作行为级别
     */
    @JsonProperty
    private Integer level;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;

    /**
     * 上次更新时间
     */
    @JsonIgnore
    private Date updateTime;

    /**
     * 逻辑删除标识: 已删除(1), 未删除(0)
     */
    @JsonIgnore
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}