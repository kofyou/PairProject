package jiekou.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.security.Key;

public class Keyword {
    private Integer keyword_count_id;
    private String content;
    private Integer count;

//    public Keyword(){
//
//    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getKeyword_id() {
        return keyword_count_id;
    }

    public void setKeyword_id(Integer keyword_id) {
        this.keyword_count_id = keyword_id;
    }

    public Keyword(Integer keyword_id, String content,Integer count) {
        this.keyword_count_id = keyword_id;
        this.content = content;
        this.count=count;
    }
}