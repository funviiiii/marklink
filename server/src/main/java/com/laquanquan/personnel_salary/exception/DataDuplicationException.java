package com.laquanquan.personnel_salary.exception;

/**
 * @author lqq
 */
public class DataDuplicationException extends RuntimeException {
    public DataDuplicationException() {
    }

    public DataDuplicationException(String message) {
        super(message);
    }

    public DataDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataDuplicationException(Throwable cause) {
        super(cause);
    }

    public DataDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
