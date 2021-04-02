package com.yrc.config;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken {//继承UsernamePasswordToken添加loginType属性
    private String loginType;

    public UserToken() {}

    public UserToken(final String username, final String password,
                     final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}