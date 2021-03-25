package entity;

public class items {
    private String id;
    private String abstracts;
    private String conference;
    private String keyword;
    private String time;
    private String title;
    private String link;
    
    public items()
    {
        
    }
    
    public items(String id,String abstracts,String conference,String keyword,String time,String title,String link)
    {
        this.id = id;
        this.abstracts = abstracts;
        this.conference = conference;
        this.keyword = keyword;
        this.time = time;
        this.title = title;
        this.link = link;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAbstracts() {
        return abstracts;
    }
    public void setAbstracts(String abstracts) {
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
    


    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(this==obj)
        {
            return true;
        }
        if(obj instanceof items)
        {
            items i = (items)obj;
            if(this.getId()==i.getId()&&this.getTitle().equals(i.getTitle()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "论文编号："+this.getId()+",论文名称："+this.getTitle();
    }
}
