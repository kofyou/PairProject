package com.yangyu.esearch.entity;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.entity.WordYear
 * @Description : 类描述
 * @date : 2021-03-30 14:14:49
 * Copyright  2021 user. All rights reserved.
 */
public class WordYear {
    private String []keyword;
    private String []years;

    public String[] getKeyword() {
        return keyword;
    }

    public void setKeyword(String[] keyword) {
        this.keyword = keyword;
    }

    public String[] getYears() {
        return years;
    }

    public void setYears(String[] years) {
        this.years = years;
    }
}
