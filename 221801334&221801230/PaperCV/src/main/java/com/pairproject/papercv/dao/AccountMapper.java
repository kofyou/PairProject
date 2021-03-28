package com.pairproject.papercv.dao;

import com.pairproject.papercv.model.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 *
 * @date 2021/03/28
 */
@Mapper
public interface AccountMapper {

    /**
     * 通过用户名查询用户
     *
     * @param name 用户名
     * @return 用户
     */
    Account selectByName(String name);

    /**
     * 插入用户
     *
     * @param account 用户
     */
    void insert(Account account);

}
