package com.pairproject.papercv.controller;

import com.pairproject.papercv.model.Account;
import com.pairproject.papercv.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @date 2021/03/28
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 登录
     *
     * @param account
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody Account account) {
        return accountService.login(account);
    }

    /**
     * 注册
     *
     * @param account
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestBody Account account) {
        return accountService.register(account);
    }

}
