package com.laquanquan.personnel_salary.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Zyxxxxxi
 * @version 1.0.0
 * @createDate 2022/10/31
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WebResponseBody<T> {
    @JsonProperty
    private String statusCode;
    @JsonProperty
    private String statusMsg;
    @JsonProperty
    private T contentValue;
}
