package edu.fzu.paperAnalysis.vo;

/**
 * @ClassName Paper
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/3/25 19:01
 * @Version 1.0
 **/
public class Paper {
    private String paperTitle;
    private String paperTypeYear;
    private String paperKeyword;
    private String paperReleasetime;
    private String paperAbstract;
    private String paperLink;

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperTypeYear() {
        return paperTypeYear;
    }

    public void setPaperTypeYear(String paperTypeYear) {
        this.paperTypeYear = paperTypeYear;
    }

    public String getPaperKeyword() {
        return paperKeyword;
    }

    public void setPaperKeyword(String paperKeyword) {
        this.paperKeyword = paperKeyword;
    }

    public String getPaperReleasetime() {
        return paperReleasetime;
    }

    public void setPaperReleasetime(String paperReleasetime) {
        this.paperReleasetime = paperReleasetime;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getPaperLink() {
        return paperLink;
    }

    public void setPaperLink(String paperLink) {
        this.paperLink = paperLink;
    }
}
