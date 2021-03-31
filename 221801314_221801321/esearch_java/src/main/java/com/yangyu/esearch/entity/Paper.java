package com.yangyu.esearch.entity;

import java.util.List;
import java.util.Map;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.entity.test
 * @Description : 论文实体类
 * @date : 2021-03-23 21:09:07
 * Copyright  2021 user. All rights reserved.
 */
public class Paper {
    private Integer id;
    private String title;
    private String link;
    private String abstracts;
    private String years;
    private String keyword;
    private String source;
    private String authors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
