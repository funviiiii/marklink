package com.laquanquan.personnel_salary.exception;

/**
 * @author lqq
 */
public class UserInfoInvalidException extends RuntimeException{
    public UserInfoInvalidException() {
    }

    public UserInfoInvalidException(String message) {
        super(message);
    }

    public UserInfoInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoInvalidException(Throwable cause) {
        super(cause);
    }

    public UserInfoInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
