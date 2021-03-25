package com.example.demo.service;

import com.example.demo.bean.Paper;
import com.example.demo.bean.User;

import java.util.List;

public interface IndexSerice
{
    public User login(User user);
    public List<Paper> getPaper();
}
