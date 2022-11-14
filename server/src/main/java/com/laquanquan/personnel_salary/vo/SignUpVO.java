package com.laquanquan.personnel_salary.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lqq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpVO {
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String email;

    @JsonProperty
    private String phone;

    @JsonProperty
    private String name;

    @JsonProperty
    private String gender;

    @JsonProperty
    private Date birthday;

    @JsonProperty
    private Integer isMarried;

    public Account getAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhone(phone);
        return account;
    }

    public User getUser() {
        User user = new User();
        user.setName(name);
        user.setBirthday(birthday);
        user.setGender(gender);
        user.setIsMarried(isMarried);
        return user;
    }
}
