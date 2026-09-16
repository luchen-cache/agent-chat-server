package com.wzh.common;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author luchen
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 业务状态码
     */
    private final int code;

    /**
     * 构造业务异常
     *
     * @param code    业务状态码
     * @param message 异常信息
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
