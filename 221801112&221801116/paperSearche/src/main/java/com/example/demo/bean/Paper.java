package com.example.demo.bean;

import java.util.ArrayList;
import java.util.List;

public class Paper
{
    String paperId;
    List<PaperAuthors> authorsList=new ArrayList<>();
    List<Keywords> keywordsList=new ArrayList<>();
    String authors;//字符串形式
    String keywords;//
    String abstrac;
    String publicationTitle;
    String publicationYear;
    String persistentLink;
    String conference;

    @Override
    public String toString()
    {
        return "Paper{" +
                "paperId='" + paperId + '\'' +
                ", authorsList=" + authorsList +
                ", keywordsList=" + keywordsList +
                ", authors='" + authors + '\'' +
                ", keywords='" + keywords + '\'' +
                ", abstrac='" + abstrac + '\'' +
                ", publicationTitle='" + publicationTitle + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", persistentLink='" + persistentLink + '\'' +
                ", conference='" + conference + '\'' +
                '}';
    }

    public String getConference()
    {
        return conference;
    }

    public void setConference(String conference)
    {
        this.conference = conference;
    }

    public List<PaperAuthors> getAuthorsList()
    {
        return authorsList;
    }

    public void setAuthorsList(List<PaperAuthors> authorsList)
    {
        this.authorsList = authorsList;
    }

    public List<Keywords> getKeywordsList()
    {
        return keywordsList;
    }

    public void setKeywordsList(List<Keywords> keywordsList)
    {
        this.keywordsList = keywordsList;
    }

    public String getAbstrac()
    {
        return abstrac;
    }

    public void setAbstrac(String abstrac)
    {
        this.abstrac = abstrac;
    }

    public String getPublicationTitle()
    {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle)
    {
        this.publicationTitle = publicationTitle;
    }

    public String getPublicationYear()
    {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear)
    {
        this.publicationYear = publicationYear;
    }

    public String getPersistentLink()
    {
        return persistentLink;
    }

    public void setPersistentLink(String persistentLink)
    {
        this.persistentLink = persistentLink;
    }

    public String getPaperId()
    {
        return paperId;
    }

    public void setPaperId(String paperId)
    {
        this.paperId = paperId;
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
}
