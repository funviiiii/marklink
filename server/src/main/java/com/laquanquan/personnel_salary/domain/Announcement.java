package com.laquanquan.personnel_salary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laquanquan.personnel_salary.vo.AnnouncementVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqq
 * @TableName t_announcement
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement implements Serializable {
    /**
     * 主键
     * -- GETTER --
     *  主键
     * -- SETTER --
     *  主键


     */
    @JsonProperty
    private Long id;

    /**
     * 公告标题
     * -- GETTER --
     *  公告标题
     * -- SETTER --
     *  公告标题


     */
    @JsonProperty
    private String title;

    /**
     * 正文
     * -- GETTER --
     *  正文
     * -- SETTER --
     *  正文


     */
    @JsonProperty
    private String text;

    /**
     * 创建者用户编号
     * -- GETTER --
     *  创建者用户编号
     * -- SETTER --
     *  创建者用户编号


     */
    @JsonProperty
    private String creator;

    /**
     * 创建时间
     * -- GETTER --
     *  创建时间
     * -- SETTER --
     *  创建时间


     */
    @JsonIgnore
    private Date createTime;

    /**
     * 上次更新时间
     * -- GETTER --
     *  上次更新时间
     * -- SETTER --
     *  上次更新时间


     */
    @JsonIgnore
    private Date updateTime;

    /**
     * 逻辑删除标识: 已删除(1), 未删除(0)
     * -- GETTER --
     *  逻辑删除标识: 已删除(1), 未删除(0)
     * -- SETTER --
     *  逻辑删除标识: 已删除(1), 未删除(0)


     */
    @JsonIgnore
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public Announcement(AnnouncementVO announcementVO) {
        this.id = announcementVO.getId();
        this.title = announcementVO.getTitle();
        this.text = announcementVO.getText();
        this.creator = announcementVO.getCreator();
        this.createTime = announcementVO.getCreateTime();
    }
}