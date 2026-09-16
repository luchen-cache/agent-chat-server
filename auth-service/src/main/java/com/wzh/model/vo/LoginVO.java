package com.wzh.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录出参
 *
 * @author luchen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 令牌类型
     */
    private String tokenType;

    /**
     * 有效期，单位秒
     */
    private long expiresIn;

    /**
     * 当前用户信息
     */
    private UserInfo user;

    /**
     * 登录用户信息
     *
     * @author luchen
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {

        /**
         * 用户ID
         */
        private Long id;

        /**
         * 用户名
         */
        private String username;

        /**
         * 用户昵称
         */
        private String nickname;
    }
}
