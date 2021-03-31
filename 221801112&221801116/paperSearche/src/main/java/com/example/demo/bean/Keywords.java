package com.example.demo.bean;

import java.util.List;

/**
 * @Description: 关键词
 * @Author: 曹鑫
 * @Date: 2021/3/31
 */
public class Keywords {
    String type;
    List<String> kedList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getKedList() {
        return kedList;
    }

    public void setKedList(List<String> kedList) {
        this.kedList = kedList;
    }
}
