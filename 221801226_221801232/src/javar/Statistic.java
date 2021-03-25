package javar;


public class Statistic {
    private String abstracts;
    private String conference;
    private String keyword;
    private String time;
    private String title;
    private String link;

    public String getAbstract(){
        return abstracts;
    }
    public void setAbstact(String abstracts){
        this.abstracts = abstracts;
    }

    public String getConference(){
        return conference;
    }
    public void setConference(String conference){
        this.conference = conference;
    }
    public String getKeyword(){
        return keyword;
    }
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }
    
}
