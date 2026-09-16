package com.wzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 * 用户认证服务
 *
 * @author luchen
 */
@SpringBootApplication(
        scanBasePackages = {"com.wzh"},
        exclude = DataSourceAutoConfiguration.class
)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
