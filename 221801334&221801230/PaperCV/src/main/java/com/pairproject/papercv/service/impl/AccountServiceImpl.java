package com.pairproject.papercv.service.impl;

import com.pairproject.papercv.dao.AccountMapper;
import com.pairproject.papercv.model.Account;
import com.pairproject.papercv.service.AccountService;
import com.pairproject.papercv.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AccountService实现类
 *
 * @date 2021/03/28
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 登录
     *
     * @param account 用户
     * @return 信息
     */
    @Override
    public String login(Account account) {
        Account account1 = accountMapper.selectByName(account.getName());
        if (account1 == null){
            return "用户名不存在";
        }
        if (!account1.getPassword().equals(MD5Util.md5Password(account.getPassword()))) {
            return "密码错误";
        }
        return "登录成功";
    }

    /**
     * 注册
     *
     * @param account 用户
     * @return 信息
     */
    @Override
    public String register(Account account) {
        Account account1 = accountMapper.selectByName(account.getName());
        if (account1 != null){
            return "用户名已存在";
        }
        account.setPassword(MD5Util.md5Password(account.getPassword()));
        accountMapper.insert(account);
        return "注册成功";
    }
}
