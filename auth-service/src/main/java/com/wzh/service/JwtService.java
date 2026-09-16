package com.wzh.service;

import com.wzh.config.JwtProperties;
import com.wzh.model.po.UserPO;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

/**
 * JWT令牌服务
 *
 * @author luchen
 */
@Service
public class JwtService {

    private static final Base64.Encoder BASE64_URL_ENCODER = Base64.getUrlEncoder().withoutPadding();
    private static final String HEADER = BASE64_URL_ENCODER.encodeToString(
            "{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
    private final JwtProperties properties;

    public JwtService(JwtProperties properties) {
        this.properties = properties;
    }

    /**
     * 创建访问令牌
     *
     * @param user 用户信息
     * @return JWT访问令牌
     */
    public String createAccessToken(UserPO user) {
        Instant now = Instant.now();
        String payload = "{\"sub\":" + quote(user.getUsername())
                + ",\"uid\":" + user.getId()
                + ",\"iat\":" + now.getEpochSecond()
                + ",\"exp\":" + now.plus(properties.getExpiration()).getEpochSecond()
                + ",\"jti\":" + quote(UUID.randomUUID().toString()) + "}";
        String content = HEADER + "." + BASE64_URL_ENCODER.encodeToString(payload.getBytes(StandardCharsets.UTF_8));
        return content + "." + sign(content);
    }

    /**
     * 获取访问令牌有效期秒数
     *
     * @return 有效期秒数
     */
    public long expirationSeconds() {
        return properties.getExpiration().toSeconds();
    }

    private String quote(String value) {
        StringBuilder result = new StringBuilder(value.length() + 2).append('"');
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            switch (character) {
                case '"' -> result.append("\\\"");
                case '\\' -> result.append("\\\\");
                case '\b' -> result.append("\\b");
                case '\f' -> result.append("\\f");
                case '\n' -> result.append("\\n");
                case '\r' -> result.append("\\r");
                case '\t' -> result.append("\\t");
                default -> {
                    if (character < 0x20) {
                        result.append(String.format("\\u%04x", (int) character));
                    } else {
                        result.append(character);
                    }
                }
            }
        }
        return result.append('"').toString();
    }

    private String sign(String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(properties.getSecret().getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return BASE64_URL_ENCODER.encodeToString(mac.doFinal(content.getBytes(StandardCharsets.US_ASCII)));
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to sign JWT", exception);
        }
    }
}
