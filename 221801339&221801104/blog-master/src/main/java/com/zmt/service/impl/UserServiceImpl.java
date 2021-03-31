package com.zmt.service.impl;

import com.zmt.dao.UserRepository;
import com.zmt.po.User;
import com.zmt.service.Userservice;
import com.zmt.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements Userservice {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        System.out.println(MD5Utils.code(password));
        return user;
    }
}
