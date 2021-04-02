package com.pairproject.papercv.service;

import com.pairproject.papercv.model.Account;

/**
 * Account业务处理
 *
 * @date 2021/03/28
 */
public interface AccountService {

    /**
     * 登录
     *
     * @param account 用户
     * @return 信息
     */
    String login(Account account);

    /**
     * 注册
     *
     * @param account 用户
     * @return 信息
     */
    String register(Account account);

}
