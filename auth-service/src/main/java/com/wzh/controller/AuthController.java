package com.wzh.controller;

import com.wzh.model.vo.ResultVO;
import com.wzh.model.dto.LoginDTO;
import com.wzh.model.vo.LoginVO;
import com.wzh.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证相关接口
 *
 * @author luchen
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    /**
     * 用户登录
     *
     * @param request 登录参数
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultVO<LoginVO> login(@Valid @RequestBody LoginDTO request) {
        return ResultVO.success(authService.login(request));
    }
}
