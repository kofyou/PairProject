package com.example.demo.service.serviceImpl;

import com.example.demo.bean.Keywords;
import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperAuthors;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IndexSerice;
import com.example.demo.utils.FileUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexSerice
{
    final Integer INI_SIZE=1024;
    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user)
    {
        System.out.println("进入service层:"+user.getName());
        User  tmp=userMapper.selUserByName(user.getName());
        System.out.println("获得的USER："+tmp.getName());
        if (user.getPassword()==null||!(user.getPassword().equals(tmp.getPassword())))
            return null;
        return tmp;
    }

    public List<Paper> getAllPaper()
    {
        return userMapper.selAllPaper();
    }


    @Override
    public List<Paper> getPaper()
    {
        FileReader reader = null;
        List<String> list = new ArrayList<>();
        StringBuilder sb;
        String[] path={"E:\\其他\\三下\\软件工程\\结对作业2\\数据\\论文数据\\CVPR（2000年至2020年，6916篇",
                "E:\\其他\\三下\\软件工程\\结对作业2\\数据\\论文数据\\ICCV（2001年至2019年，3196篇）"};

        String[] conference={"CVPR","ICCV"};
        for (int i=0;i<2;i++)
        {
            List<File> fileList= FileUtil.getFiles(path[i]);

            for (File f : fileList)
            {
                String tmp=null;
                System.out.println("数据转储中");
                System.out.println(path[i]+"\\"+f.getName());
                sb=new StringBuilder(INI_SIZE);
                //读取json文件
                try
                {
                    reader = new FileReader(path[i]+"\\"+f.getName());
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    while ((tmp=bufferedReader.readLine())!=null)
                    {
                        sb.append(tmp);
                    }
                    list.add(sb.toString());
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

//                System.out.println("****** "+conference[i]);
                Paper paper=onepaperJsonToPaper(sb.toString(),conference[i]);
//            //保存至数据库
            if (paper!=null)
                userMapper.insPaper(paper);
            }
        }

        //转为对象
        return papersJsonToPaper(list);
    }

        //基于第三方fastjson解析json数据，由于是第三方库，处里报错故未使用。
//    private List<Paper> papersJsonToPaper(List<String> list)
//    {
//        List<Paper> list1=new ArrayList<>();
//        //json串改转为对象
//        for (int i=0;i<list.size();i++)
//        {
//            System.out.println(list.get(i));
//            Paper paper=JSON.parseObject(list.get(i),Paper.class);
//            list1.add(paper);
//        }
//
//        return list1;
//    }

//    基于jackson对json文件进行解析。
    private List<Paper> papersJsonToPaper(List<String> list)
    {
        ObjectMapper objectMapper=new ObjectMapper();
        List<Paper> paperList=new ArrayList<>();
        for (int i=0;i<list.size();i++)
        {
            JsonNode node= null;
            try
            {
                node = objectMapper.readTree(list.get(i));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            logger.debug(String.valueOf(node.get("abstract")));
            System.out.println("取得的摘要："+String.valueOf(node.get("abstract")));
            Paper paper = new Paper();
            paper.setAbstrac(String.valueOf(node.get("abstract")));
            paper.setPersistentLink(String.valueOf(node.get("htmlLink")));
            paper.setPublicationTitle(String.valueOf(node.get("title")));
            paper.setPublicationYear(String.valueOf(node.get("publicationYear")));
            List<PaperAuthors> authorslist = paper.getAuthorsList();
            List<Keywords> keywordsList=paper.getKeywordsList();
            //关键词对象。
            for (int j=0;node.get("keywords")!=null&&j<node.get("keywords").size();j++)
            {
                System.out.println("获得的关键词:"+String.valueOf(node.get("keywords").get(j)));
                Keywords keywords=new Keywords();
                keywords.setType(String.valueOf(node.get("keywords").get(j).get("type")));
                List<String> stringList=new ArrayList<>();
                for (int k=0;k<node.get("keywords").get(j).get("kwd").size();k++)
                {
                    stringList.add(String.valueOf(node.get("keywords").get(j).get("kwd").get(k)));
                    //利用,分割，以string形式保存
                    if (paper.getKeywords()==null)
                        paper.setKeywords(String.valueOf(node.get("keywords").get(j).get("kwd").get(k)));
                    else
                        paper.setKeywords(paper.getKeywords()+","+node.get("keywords").get(j).get("kwd").get(k).toString());
                }
                keywords.setKedList(stringList);
                keywordsList.add(keywords);
            }
            paper.setKeywordsList(keywordsList);
            System.out.println("获得的paper:"+paper.toString());
            paperList.add(paper);
//            authorslist.add(new PaperAuthors(node.get("keywords")))
        }
        return paperList;
    }

    private Paper onepaperJsonToPaper(String str,String conference)
    {
        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode node= null;
        try
        {
            node = objectMapper.readTree(str);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        logger.debug(String.valueOf(node.get("abstract")));
        System.out.println("取得的摘要："+String.valueOf(node.get("abstract")));
        Paper paper = new Paper();
        paper.setConference(conference);
        paper.setAbstrac(String.valueOf(node.get("abstract")));
        paper.setPersistentLink(String.valueOf(node.get("htmlLink")));
        paper.setPublicationTitle(String.valueOf(node.get("title")));
        paper.setPublicationYear(String.valueOf(node.get("publicationYear")));
        List<PaperAuthors> authorslist = paper.getAuthorsList();
        List<Keywords> keywordsList=paper.getKeywordsList();
        //关键词对象。
        for (int j=0;node.get("keywords")!=null&&j<node.get("keywords").size();j++)
        {
            System.out.println("获得的关键词:"+String.valueOf(node.get("keywords").get(j)));
            Keywords keywords=new Keywords();
            keywords.setType(String.valueOf(node.get("keywords").get(j).get("type")));
            List<String> stringList=new ArrayList<>();
            for (int k=0;k<node.get("keywords").get(j).get("kwd").size();k++)
            {
                stringList.add(String.valueOf(node.get("keywords").get(j).get("kwd").get(k)));
                //利用,分割，以string形式保存
                if (paper.getKeywords()==null)
                    paper.setKeywords(String.valueOf(node.get("keywords").get(j).get("kwd").get(k)));
                else
                    paper.setKeywords(paper.getKeywords()+","+node.get("keywords").get(j).get("kwd").get(k).toString());
            }
            keywords.setKedList(stringList);
            keywordsList.add(keywords);
        }
        paper.setKeywordsList(keywordsList);
        System.out.println("******"+paper.getConference());
        System.out.println("获得的paper:"+paper.toString());
//            authorslist.add(new PaperAuthors(node.get("keywords")))
        return paper;
    }
}
