package com.laquanquan.personnel_salary.domain;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_announcement
 */
@Setter
@Data
public class Announcement implements Serializable {
    /**
     * 主键
     * -- GETTER --
     *  主键
     * -- SETTER --
     *  主键


     */
    private Long id;

    /**
     * 公告标题
     * -- GETTER --
     *  公告标题
     * -- SETTER --
     *  公告标题


     */
    private String title;

    /**
     * 正文
     * -- GETTER --
     *  正文
     * -- SETTER --
     *  正文


     */
    private String text;

    /**
     * 创建者用户编号
     * -- GETTER --
     *  创建者用户编号
     * -- SETTER --
     *  创建者用户编号


     */
    private String creator;

    /**
     * 创建时间
     * -- GETTER --
     *  创建时间
     * -- SETTER --
     *  创建时间


     */
    private Date createTime;

    /**
     * 上次更新时间
     * -- GETTER --
     *  上次更新时间
     * -- SETTER --
     *  上次更新时间


     */
    private Date updateTime;

    /**
     * 逻辑删除标识: 已删除(1), 未删除(0)
     * -- GETTER --
     *  逻辑删除标识: 已删除(1), 未删除(0)
     * -- SETTER --
     *  逻辑删除标识: 已删除(1), 未删除(0)


     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}