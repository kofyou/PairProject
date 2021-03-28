package cn.itcast.domain;

public class PaperInfo {
    private String title;
    private String author;
    private String year;
    private String meeting;
    private String abstr;
    private String url;
    private String accesstimes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccesstimes() {
        return accesstimes;
    }

    public void setAccesstimes(String accesstimes) {
        this.accesstimes = accesstimes;
    }
}
