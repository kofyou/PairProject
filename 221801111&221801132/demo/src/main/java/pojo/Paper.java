package pojo;

public class Paper {
    private String title;
    private String summary;
    private String link;
    private String keyword;
    private String year;
    private String type;

    public void setTitle(String s) { this.title = s; }

    public void setSummary(String s) { this.summary = s; }

    public void setLink(String s) { this.link = s; }

    public void setKeyword(String s) { this.keyword = s; }

    public void setYear(String s) { this.year = s; }

    public void setType(String s) { this.type = s; }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }
}
