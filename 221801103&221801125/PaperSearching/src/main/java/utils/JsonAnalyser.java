package utils;

import dao.PaperDaoimpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.mortbay.util.ajax.JSON;
import pojo.Paper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonAnalyser
{
    public static List<Paper> Analyse(String path,String theConference)
    {
        List<Paper> list=new ArrayList<Paper>();
        try
        {
            File file = new File(path);
            if (file.isFile())
            {
                return null;
            }
            else
            {
                File[] files = file.listFiles();
                for (int k=0;k<files.length;k++)
                {
                    Paper paper=new Paper();
                    String isbn;
                    String title;
                    String authors = "";
                    String keywords = "";
                    String theabstract;
                    String publishDate;
                    String conference;
                    String paperlink;

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(files[k]));

                    String paperString = bufferedReader.readLine();
                    JSONObject jsonObject = JSONObject.fromObject(paperString.substring(0, paperString.length() - 1));

                    /*isbn = JSONObject.fromObject(
                            jsonObject.getJSONArray("isbn").get(0)).getString("value");*/

                    isbn=jsonObject.getString("articleNumber");
                    title = jsonObject.getString("title");

                    JSONArray authorArray = jsonObject.getJSONArray("authors");
                    for (int i = 0; i < authorArray.size(); i++) {

                        authors += JSONObject.fromObject(
                                jsonObject.getJSONArray("authors").get(i)).getString("name") + "//";
                    }

                    if (jsonObject.containsKey("keywords"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("keywords");
                        for (int i = 0; i < jsonArray.size(); i++)
                        {
                            JSONObject temp = JSONObject.fromObject(jsonArray.get(i));
                            String kwd = temp.getString("kwd");
                            JSONArray jsonArray1 = JSONArray.fromObject(kwd);
                            Object[] objects = jsonArray1.toArray();
                            for (Object keyword : objects) {

                                keywords += keyword + "//";
                            }
                        }
                    }
                    else
                    {
                        authors="";
                    }

                    if (jsonObject.containsKey("abstract"))
                    {
                        theabstract = jsonObject.getString("abstract");
                    }
                    else {
                        theabstract="";
                    }

                    if (jsonObject.containsKey("chronOrPublicationDate"))
                    {
                        publishDate = jsonObject.getString("chronOrPublicationDate");
                    }
                    else {
                        publishDate="";
                    }


                    conference = theConference;

                    paperlink=jsonObject.getString("doiLink");

                    paper.setIsbn(isbn);
                    paper.setTitle(title);
                    paper.setAuthors(authors);
                    paper.setKeywords(keywords);
                    paper.setTheabstract(theabstract);
                    paper.setPublishDate(publishDate);
                    paper.setConferrence(conference);
                    paper.setPaperlink(paperlink);

                    System.out.println(paper.toString());
                    list.add(paper);

                }

            }
            System.out.println("返回List");
            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Paper> Analyse1(String path,String theConference)
    {
        List<Paper> list=new ArrayList<Paper>();
        try
        {
            File file = new File(path);
            if (file.isFile())
            {
                return null;
            }
            else
            {
                File[] files = file.listFiles();
                for (int k=0;k<files.length;k++)
                {

                    Paper paper=new Paper();
                    String isbn;
                    String title;
                    String authors = "";
                    String keywords = "";
                    String theabstract;
                    String publishDate;
                    String conference;
                    String paperlink;

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(files[k]));

                    String paperString="";
                    String temp;
                    while((temp = bufferedReader.readLine())!=null)
                    {
                        paperString+=temp;
                    }
                    System.out.println(paperString);
                    JSONObject jsonObject = JSONObject.fromObject(paperString);

                    /*isbn = JSONObject.fromObject(
                            jsonObject.getJSONArray("isbn").get(0)).getString("value");*/

                    isbn=Integer.toString(k);
                    title = jsonObject.getString("论文名称");


                    if (jsonObject.containsKey("关键词"))
                    {
                        String kwd = jsonObject.getString("关键词");
                        JSONArray jsonArray=JSONArray.fromObject(kwd);
                        Object[] objects = jsonArray.toArray();
                        for (Object keyword : objects) {

                            keywords += keyword + "//";
                        }
                    }
                    else
                    {
                        authors="";
                    }

                    if (jsonObject.containsKey("摘要"))
                    {
                        theabstract = jsonObject.getString("摘要");
                    }
                    else {
                        theabstract="";
                    }

                    if (jsonObject.containsKey("发布时间"))
                    {
                        publishDate = jsonObject.getString("发布时间");
                    }
                    else {
                        publishDate="";
                    }


                    conference = theConference;

                    paperlink=jsonObject.getString("原文链接");

                    paper.setIsbn(isbn);
                    paper.setTitle(title);
                    paper.setAuthors(authors);
                    paper.setKeywords(keywords);
                    paper.setTheabstract(theabstract);
                    paper.setPublishDate(publishDate);
                    paper.setConferrence(conference);
                    paper.setPaperlink(paperlink);

                    System.out.println(paper.toString());
                    list.add(paper);

                }

            }
            System.out.println("返回List");
            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String args[])
    {
        PaperDaoimpl paperDaoimpl=new PaperDaoimpl();
        /*paperDaoimpl.PaperStorage(JsonAnalyser.Analyse(
                "C:\\Users\\韩小韬\\Desktop\\论文数据\\论文数据\\ICCV（2001年至2019年，3196篇）",
                "iccv"));*/
        paperDaoimpl.PaperStorage(JsonAnalyser.Analyse1(
                "C:\\Users\\韩小韬\\Desktop\\论文数据\\论文数据\\ECCV（2016至2020，3033份）",
                "eccv"));

    }
}
