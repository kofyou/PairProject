package com.zmt.dao;

import com.zmt.po.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
