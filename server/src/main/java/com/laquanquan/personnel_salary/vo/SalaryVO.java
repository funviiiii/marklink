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
public class SalaryVO {
    @JsonProperty
    private String uid;

    @JsonProperty
    private String name;

    @JsonProperty
    private String basicSalary;

    @JsonProperty
    private String allowance;

    @JsonProperty
    private String reward;

    @JsonProperty
    private String shouldPay;

    @JsonProperty
    private String cost;

    @JsonProperty
    private String insurance;

    @JsonProperty
    private String realSalary;
}
