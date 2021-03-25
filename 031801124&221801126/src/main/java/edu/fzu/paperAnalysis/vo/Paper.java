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
    private String paperAuthor;
    private String paperYear;
    private String paperKeywords;
    private String paperPublication;
    private String paperAbstract;

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAuthor() {
        return paperAuthor;
    }

    public void setPaperAuthor(String paperAuthor) {
        this.paperAuthor = paperAuthor;
    }

    public String getPaperYear() {
        return paperYear;
    }

    public void setPaperYear(String paperYear) {
        this.paperYear = paperYear;
    }

    public String getPaperKeywords() {
        return paperKeywords;
    }

    public void setPaperKeywords(String paperKeywords) {
        this.paperKeywords = paperKeywords;
    }

    public String getPaperPublication() {
        return paperPublication;
    }

    public void setPaperPublication(String paperPublication) {
        this.paperPublication = paperPublication;
    }
}
