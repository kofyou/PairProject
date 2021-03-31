package cn.edu.fzu.papermanage.entity;

import javax.persistence.*;

/**
 * 对应keyword_with_frequency视图的实体类
 */
@Table(name = "keyword_with_frequency")
@Entity
@IdClass(KeywordWithFrequencyPrimaryKey.class)
public class KeywordWithFrequency {
    @Id
    @Column(name = "keyword")
    private String keyword;

    @Id
    @Column(name = "publishyear")
    private String publishYear;

    @Id
    @Column(name = "source")
    private String source;

    @Column(name = "frequency")
    private Integer frequency;

    public KeywordWithFrequency() {
    }

    public KeywordWithFrequency(String keyword, String publishYear, String source, Integer frequency) {
        this.keyword = keyword;
        this.publishYear = publishYear;
        this.source = source;
        this.frequency = frequency;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "KeywordWithFrequency{" +
                "keyword='" + keyword + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", source='" + source + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
