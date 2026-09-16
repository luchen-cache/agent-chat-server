package com.wzh.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应VO
 *
 * @param <T> 业务数据类型
 * @author luchen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "success";

    /**
     * 业务状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 构造无业务数据的成功响应
     *
     * @return 成功响应
     */
    public static ResultVO<Void> success() {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    /**
     * 构造带业务数据的成功响应
     *
     * @param data 业务数据
     * @param <T>  业务数据类型
     * @return 成功响应
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    /**
     * 构造失败响应
     *
     * @param code 业务状态码
     * @param msg  响应消息
     * @return 失败响应
     */
    public static ResultVO<Void> error(int code, String msg) {
        return new ResultVO<>(code, msg, null);
    }
}
