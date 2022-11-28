package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Long id;

    /**
     * 职位编号
     */
    @JsonProperty
    private String rid;

    /**
     * 职称
     */
    @JsonProperty
    private String roleName;

    /**
     * 是否有管理人事的权力
     */
    @JsonProperty
    private Integer personnelRight;

    /**
     * 是否有管理工资的权力
     */
    @JsonProperty
    private Integer salaryRight;

    /**
     * 是否有管理基本信息的权力（部门，职位等）
     */
    @JsonProperty
    private Integer infoRight;

    /**
     * 是否有管理系统的权力
     */
    @JsonProperty
    private Integer advanceRight;

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