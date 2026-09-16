package com.wzh.service;

import com.wzh.common.BusinessException;
import com.wzh.mapper.UserMapper;
import com.wzh.model.dto.LoginDTO;
import com.wzh.model.po.UserPO;
import com.wzh.model.vo.LoginVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 *
 * @author luchen
 */
@Service
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * 用户登录
     *
     * @param request 登录参数
     * @return 登录结果
     */
    public LoginVO login(LoginDTO request) {
        UserPO user = userMapper.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BusinessException(40101, "用户名或密码错误");
        }
        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new BusinessException(40102, "用户已被禁用");
        }

        String token = jwtService.createAccessToken(user);
        LoginVO.UserInfo userInfo = new LoginVO.UserInfo(
                user.getId(), user.getUsername(), user.getNickname());
        return new LoginVO(token, "Bearer", jwtService.expirationSeconds(), userInfo);
    }
}
