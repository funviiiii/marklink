package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lqq
 * @TableName t_salary
 */
@Data
public class Salary implements Serializable {
    /**
     * 主键
     */
    @JsonIgnore
    private Long id;

    /**
     * 工资编号
     */
    @JsonProperty
    private String sid;

    /**
     * 用户编号
     */
    @JsonProperty
    private String uid;

    /**
     * 基本工资
     */
    @JsonProperty
    private BigDecimal basicSalary;

    /**
     * 岗位津贴
     */
    @JsonProperty
    private BigDecimal allowance;

    /**
     * 奖励
     */
    @JsonProperty
    private BigDecimal reward;

    /**
     * 应付工资
     */
    @JsonProperty
    private BigDecimal shouldPay;

    /**
     * 其他花销
     */
    @JsonProperty
    private BigDecimal cost;

    /**
     * 保险
     */
    @JsonProperty
    private BigDecimal insurance;

    /**
     * 实发工资
     */
    @JsonProperty
    private BigDecimal realSalary;

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
