package com.laquanquan.personnel_salary.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laquanquan.personnel_salary.domain.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementVO {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String text;
    @JsonProperty
    private String creator;
    @JsonProperty
    private String creatorToken;
    @JsonProperty
    private Date createTime;
    @JsonProperty
    private String token;

    public AnnouncementVO(Announcement announcement) {
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.text = announcement.getText();
        this.creator = announcement.getCreator();
        this.createTime = announcement.getCreateTime();
    }
}
