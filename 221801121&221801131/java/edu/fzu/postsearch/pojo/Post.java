package edu.fzu.postsearch.pojo;

import java.util.List;

public class Post {
    private int id;
    private String title;
    private List<String> kwds;
    private String abs;
    private String link;
    private String year;
    private String type;

    public Post(int id,String title,List<String> kwds,String abs,String link,String year,String type){
        this.id = id;
        this.title = title;
        this.kwds = kwds;
        this.abs = abs;
        this.link = link;
        this.year = year;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        this.title = link;
    }

    public List<String> getKwds() {
        return kwds;
    }

    public void setKwds(List<String> kwds) {
        this.kwds = kwds;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public  void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

}
