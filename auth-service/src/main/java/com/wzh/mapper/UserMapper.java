package com.wzh.mapper;

import com.wzh.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 *
 * @author luchen
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息，不存在时返回null
     */
    @Select("""
            SELECT id, username, password_hash, nickname, status, created_at, updated_at
            FROM sys_user
            WHERE username = #{username}
            LIMIT 1
            """)
    UserPO findByUsername(String username);
}
