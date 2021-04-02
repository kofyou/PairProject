package com.pairproject.papercv.model;

/**
 * 用户类
 *
 * @date 2021/03/28
 */
public class Account {
    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
