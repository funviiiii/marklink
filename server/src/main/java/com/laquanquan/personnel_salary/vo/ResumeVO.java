package com.laquanquan.personnel_salary.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lqq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeVO {
    @JsonProperty
    private String token;
    @JsonProperty
    private String rid;
    @JsonProperty
    private String content;
}
