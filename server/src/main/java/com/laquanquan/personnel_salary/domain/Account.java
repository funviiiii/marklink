package com.laquanquan.personnel_salary.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lqq
 * @TableName t_account
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
     * 主键
     */
    @JsonProperty
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
    private String username;

    /**
     * 密码
     */
    @JsonProperty
    private String password;

    /**
     * 邮箱
     */
    @JsonProperty
    private String email;

    /**
     * 电话号码
     */
    @JsonProperty
    private String phone;

    /**
     * 创建时间
     */
    @JsonProperty
    private Date createTime;

    /**
     * 上次更新时间
     */
    @JsonProperty
    private Date updateTime;

    /**
     * 逻辑删除标识
     */
    @JsonProperty
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}