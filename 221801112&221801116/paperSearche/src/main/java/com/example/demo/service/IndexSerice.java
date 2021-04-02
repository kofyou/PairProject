package com.example.demo.service;

import com.example.demo.bean.Paper;
import com.example.demo.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @Description: 逻辑处理接口
 * @Author: 曹鑫
 * @Date: 2021/3/31
 */
public interface IndexSerice {
    public User login(User user);

    public List<Paper> getPaper();

    public List<Map.Entry<String, Integer>> alalysePaperToGetTopKeyWords(Paper paper);
}
