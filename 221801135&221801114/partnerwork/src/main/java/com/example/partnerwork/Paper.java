package com.example.partnerwork;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Paper {
    private int id;
    private String title;
    private String abstractText;
    private String tag1 = "";
    private String tag2 = "";
    private String tag3 = "";
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

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public void setTags(){
        Pattern pattern = Pattern.compile("[,]");
        String[] tags = pattern.split(keywords);
        for (int i = 0;i < tags.length;i ++){
            if (i == 0){
                setTag1(tags[i]);
            }else if (i == 1){
                setTag2(tags[i]);
            }else if (i == 2){
                setTag3(tags[i]);
            }
        }
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
        s += getTitle();
        s += getAbstractText();
        s += getKeywords();
        s += getTag1();
        s += getTag2();
        s += getTag3();
        s += getDoiLink();
        s += getPublicationDate();
        s += getConference();
        s += "\n";
        return super.toString();
    }
}
