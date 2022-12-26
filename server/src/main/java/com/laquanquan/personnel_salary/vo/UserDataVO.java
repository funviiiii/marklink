package com.laquanquan.personnel_salary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lqq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataVO {
    private String name;
    private String gender;
    private Date birthday;
    private Date induction;
    private String department;
    private String role;
    private Boolean isMarried;
    private String resume;
}
