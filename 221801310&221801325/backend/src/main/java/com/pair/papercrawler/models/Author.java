package com.pair.papercrawler.models;


import lombok.*;

public class Author {
    private String author;
    private Integer paperId;

    public Author(String author, Integer paperId) {
        this.author = author;
        this.paperId = paperId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
