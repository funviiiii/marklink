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
public class PasswordUpdateVO {
    @JsonProperty
    private String uid;
    @JsonProperty
    private String oldPassword;
    @JsonProperty
    private String newPassword;
}
