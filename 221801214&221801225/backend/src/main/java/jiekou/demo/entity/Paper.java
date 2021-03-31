package jiekou.demo.entity;

public class Paper {

    private Integer paper_id;
    private String title;
    private String link;
    private String publication_year;
    private String magazine;
    private String abstracted;

    public void setAbstracted(String abstracted) {
        this.abstracted = abstracted;
    }

    public String getAbstracted() {
        return abstracted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getTitle() {
        return title;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public String getMagazine() {
        return magazine;
    }

    public String getLink() {
        return link;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public Paper(Integer paper_id, String title, String link, String publication_year, String magazine, String abstracted) {
        this.paper_id = paper_id;
        this.title = title;
        this.link = link;
        this.publication_year = publication_year;
        this.magazine = magazine;
        this.abstracted = abstracted;
    }

    public Paper() {

    }

}
