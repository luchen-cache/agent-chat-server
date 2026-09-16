package com.wzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户认证服务
 *
 * @author luchen
 */
@SpringBootApplication(scanBasePackages = {"com.wzh"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
