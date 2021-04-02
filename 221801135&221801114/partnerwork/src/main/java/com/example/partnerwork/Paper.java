package com.example.partnerwork;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Paper {
    private int id;
    private String title;
    private String abstractText;
    private List<String> tagList;
    private String keywords;
    private String doiLink;
    private int publicationDate;
    private String conference;

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

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public void setTagList() {
        tagList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[,]");
        String[] tags = pattern.split(keywords);
        for (String tag : tags){
            if (!tag.trim().equals("")){
                tagList.add(tag);
            }
        }
    }

    public List<String> getTagList() {
        return tagList;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDoiLink() {
        return doiLink;
    }

    public void setDoiLink(String doiLink) {
        this.doiLink = doiLink;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    @Override
    public String toString() {
        String s = "";
        s += getTitle()+ "\n";
        s += getAbstractText()+ "\n";
        s += getKeywords()+ "\n";
        //for (String tag : getTagList())
        //    s += tag+"\n";
        s += getDoiLink()+ "\n";
        s += getPublicationDate()+ "\n";
        s += getConference()+ "\n";
        s += "\n";
        return s;
    }
}
