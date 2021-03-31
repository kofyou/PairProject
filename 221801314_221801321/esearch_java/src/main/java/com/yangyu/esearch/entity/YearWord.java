package com.yangyu.esearch.entity;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.entity.YearWord
 * @Description : 类描述
 * @date : 2021-03-30 11:12:47
 * Copyright  2021 user. All rights reserved.
 */
public class YearWord {
    private String keyword;
    private int [] num;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int[] getNum() {
        return num;
    }

    public void setNum(int[] num) {
        this.num = num;
    }
}
