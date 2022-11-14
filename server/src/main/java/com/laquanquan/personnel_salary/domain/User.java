package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Long id;

    /**
     * 用户编号
     */
    @JsonProperty
    private String uid;

    /**
     * 用户名
     */
    @JsonProperty
    private String name;

    /**
     * 性别: 保密(s), 男(m), 女(f)
     */
    @JsonProperty
    private String gender;

    /**
     * 生日
     */
    @JsonProperty
    private Date birthday;

    /**
     * 入职时间
     */
    @JsonProperty
    private Date induction;

    /**
     * 部门（使用部门编号）
     */
    @JsonProperty
    private String department;

    /**
     * 职位（使用职位编号）
     */
    @JsonProperty
    private String role;

    /**
     * 是否已婚
     */
    @JsonProperty
    private Integer isMarried;

    /**
     * 简历
     */
    @JsonProperty
    private String resume;

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
     * 逻辑删除标识
     */
    @JsonIgnore
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}