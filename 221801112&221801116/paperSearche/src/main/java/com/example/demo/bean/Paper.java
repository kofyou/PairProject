package com.example.demo.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 12:55 2021/3/23
 */
public class Paper extends Model<Paper> {

    @TableId(type = IdType.AUTO)
    private Integer paperId;

    private String authors;

    private String keywords;

    private String abstrac;

    private String publicationTitle;

    private String publicationYear;

    private String persistentLink;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAbstrac() {
        return abstrac;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPersistentLink() {
        return persistentLink;
    }

    public void setPersistentLink(String persistentLink) {
        this.persistentLink = persistentLink;
    }

    @Override
    protected Serializable pkVal() {
        return this.paperId;
    }
}
