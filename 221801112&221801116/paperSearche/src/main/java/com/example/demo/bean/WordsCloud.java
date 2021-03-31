package com.example.demo.bean;

/**
 * @Description: 用于词云统计类
 * @Author: 曹鑫
 * @Date: 2021/3/31
 */
public class WordsCloud {
    String name;
    Integer weight = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
