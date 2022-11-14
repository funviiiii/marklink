package com.laquanquan.personnel_salary.exception;

/**
 * @author lqq
 */
public class AccountDuplicateException extends RuntimeException {
    public AccountDuplicateException() {
    }

    public AccountDuplicateException(String message) {
        super(message);
    }

    public AccountDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDuplicateException(Throwable cause) {
        super(cause);
    }

    public AccountDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
