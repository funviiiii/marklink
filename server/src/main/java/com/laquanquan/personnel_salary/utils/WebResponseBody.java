package com.laquanquan.personnel_salary.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author lqq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WebResponseBody<T> {
    @JsonProperty
    private String msg;
    @JsonProperty
    private T content = null;

    public WebResponseBody(String msg) {
        this.msg = msg;
    }
}
