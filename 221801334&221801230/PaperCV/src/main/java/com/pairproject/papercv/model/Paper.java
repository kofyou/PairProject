package com.pairproject.papercv.model;

/**
 * 论文类
 *
 * @date 2021/03/23
 */
public class Paper {
    private String title;// 标题
    private String paperAbstract;// 摘要
    private String url;// 论文链接
    private String meeting;// 会议
    private String year;// 年份
    private String keyWord;// 关键词（以','隔开）


    public Paper() {
        this.title = "";
        this.paperAbstract = "";
        this.url = "";
        this.meeting = "";
        this.keyWord = "";
        this.year = "";
    }

    public Paper(String title, String paperAbstract, String url, String meeting, String year, String keyWord) {
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.url = url;
        this.meeting = meeting;
        this.year = year;
        this.keyWord = keyWord;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "Paper{" +
            "title='" + title + '\'' +
            ", paperAbstract='" + paperAbstract + '\'' +
            ", url='" + url + '\'' +
            ", meeting='" + meeting + '\'' +
            ", keyWord='" + keyWord + '\'' +
            '}';
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
