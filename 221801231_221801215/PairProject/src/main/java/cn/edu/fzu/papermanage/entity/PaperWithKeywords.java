package cn.edu.fzu.papermanage.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class PaperWithKeywords {
    private Integer id;
    private String title;
    private String source;
    private String url;
    private String publishYear;
    private String _abstract;
    private List<Keyword> keywords;

    public PaperWithKeywords(Paper paper) {
        this.id = paper.getId();
        this.title = paper.getTitle();
        this.source = paper.getSource();
        this.url = paper.getUrl();
        this.publishYear = paper.getPublishYear();
        this._abstract = paper.get_abstract();
    }

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String get_abstract() {
        return _abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
