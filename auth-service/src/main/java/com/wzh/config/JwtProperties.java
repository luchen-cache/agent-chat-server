package com.wzh.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * JWT配置
 *
 * @author luchen
 */
@ConfigurationProperties(prefix = "auth.jwt")
@Getter
public class JwtProperties {

    /**
     * JWT签名密钥
     */
    private final String secret;

    /**
     * 访问令牌有效期
     */
    private final Duration expiration;

    /**
     * 构造JWT配置
     *
     * @param secret     JWT签名密钥
     * @param expiration 访问令牌有效期
     */
    public JwtProperties(String secret, Duration expiration) {
        if (secret == null || secret.getBytes(java.nio.charset.StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("auth.jwt.secret must contain at least 32 bytes");
        }
        if (expiration == null || expiration.isZero() || expiration.isNegative()) {
            throw new IllegalArgumentException("auth.jwt.expiration must be positive");
        }
        this.secret = secret;
        this.expiration = expiration;
    }
}
