package pojo;

import java.util.Date;

public class Paper
{
    private String isbn;
    private String title;
    private String authors;
    private String keywords;
    private String theabstract;
    private String publishDate;
    private String conferrence;
    private String paperlink;
    private java.sql.Date date;

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getPaperlink() {
        return paperlink;
    }

    public void setPaperlink(String paperlink) {
        this.paperlink = paperlink;
    }

    public String getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(String publishDate)
    {
        this.publishDate = publishDate;
    }

    public String getConferrence()
    {
        return conferrence;
    }

    public void setConferrence(String conferrence)
    {
        this.conferrence = conferrence;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthors()
    {
        return authors;
    }

    public void setAuthors(String authors)
    {
        this.authors = authors;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getTheabstract() {
        return theabstract;
    }

    public void setTheabstract(String theabstract) {
        this.theabstract = theabstract;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", keywords='" + keywords + '\'' +
                ", theabstract='" + theabstract + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", conferrence='" + conferrence + '\'' +
                ", paperlink='" + paperlink + '\'' +
                ", date=" + date +
                '}';
    }
}
