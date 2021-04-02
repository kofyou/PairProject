package com.company.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.util.DataFilter;


import java.util.ArrayList;

public class Paper {

    public ArrayList<String> kwd;
    public String abst;
    public ArrayList<String> author;
    public String title;
    public int year;
    public String meet;
    public String url;


    public Paper(String s, int year, String meet, String url) {
        this.year=year;
        this.meet=meet;
        this.url=url;
        kwd = new ArrayList<>();
        author = new ArrayList<>();
        s = s.trim();
        s = s.substring(s.indexOf("=") + 1, s.length() - 1);
        JSONArray jja = (JSONArray) ((JSONObject) JSONObject.parse(s)).get(DataFilter.authorsKey);
        for(Object o:jja){
            if(o!=null)
                author.add((String) ((JSONObject)o).get("name"));
        }
        this.abst = (String) ((JSONObject) JSONObject.parse(s)).get(DataFilter.abstractKey);
        this.title = (String) ((JSONObject) JSONObject.parse(s)).get(DataFilter.titleKey);
        JSONArray ja = (JSONArray) ((JSONObject) JSONObject.parse(s)).get(DataFilter.wordKey);
        for(Object o:ja){
            if(o!=null){
                Object[] os = ((JSONArray) ((JSONObject)o).get("kwd")).toArray();
                for(Object o1:os){
                    kwd.add((String)o1);
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Paper:{" +
                "keyword=" + kwd +
                ", abstract='" + abst + '\'' +
                ", authors=" + author +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", meet='" + meet + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
