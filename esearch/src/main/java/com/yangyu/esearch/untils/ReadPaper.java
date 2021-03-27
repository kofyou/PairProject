package com.yangyu.esearch.untils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yangyu.esearch.entity.Paper;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.untils.ReadPaper
 * @Description : ReadPaper类，解析本地论文JSON文件，返回结果List<Paper>
 * @date : 2021-03-26 14:17:02
 * Copyright  2021 user. All rights reserved.
 */
public class ReadPaper {

    //论文JSON文件存放路径
    private String CVPR_Root = "E:\\PaperData\\CVPR";
    private String ECCV_Root = "E:\\PaperData\\ECCV";
    private String ICCV_Root = "E:\\PaperData\\ICCV";

    //读取CVPR会议论文
    public List<Paper> readCVPR()
    {
        File root = new File(CVPR_Root);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(5000);
        for (File file : files)
        {
            String string = readFile(file);
            JSONObject jsonObject = JSON.parseObject(string.substring(0, string.length()-1));

            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("title"));
            paper.setLink(jsonObject.getString("doiLink"));
            paper.setAbstracts(jsonObject.getString("abstract"));
            paper.setYears(jsonObject.getString("publicationYear"));
            if (jsonObject.getJSONArray("keywords")==null)
            {
                continue;
            }
            if(jsonObject.getJSONArray("keywords").getJSONObject(0)==null)
            {
                continue;
            }
            if (jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd")==null)
            {
                continue;
            }
            JSONArray keywords = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            paper.setSource("CVPR");
            StringBuilder sb = new StringBuilder();
            for (Object keyword : keywords)
            {
                sb.append(keyword).append(",");
            }
            paper.setKeyword(sb.toString());
            papers.add(paper);
        }
        return papers;
    }

    //读取ECCV会议论文
    public List<Paper> readECCV()
    {
        File root = new File(ECCV_Root);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(5000);
        for (File file : files)
        {
            JSONObject jsonObject = JSON.parseObject(readFile(file));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("论文名称"));
            paper.setLink(jsonObject.getString("原文链接"));
            paper.setAbstracts(jsonObject.getString("摘要"));
            paper.setYears(jsonObject.getString("会议和年份").split(" ")[1]);
            JSONArray keywords = jsonObject.getJSONArray("关键词");
            paper.setSource("ECCV");
            StringBuilder sb = new StringBuilder();
            for (Object keyword : keywords)
            {
                sb.append(keyword).append(",");
            }
            paper.setKeyword(sb.toString());
            papers.add(paper);
        }
        return papers;
    }

    //读取ICCV会议论文
    public List<Paper> readICCV()
    {
        File root = new File(ICCV_Root);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(5000);
        for (File file : files)
        {
            String string = readFile(file);
            JSONObject jsonObject = JSON.parseObject(string.substring(0, string.length()-1));

            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("title"));
            paper.setLink(jsonObject.getString("doiLink"));
            paper.setAbstracts(jsonObject.getString("abstract"));
            paper.setYears(jsonObject.getString("publicationYear"));
            if (jsonObject.getJSONArray("keywords")==null)
            {
                continue;
            }
            if(jsonObject.getJSONArray("keywords").getJSONObject(0)==null)
            {
                continue;
            }
            if (jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd")==null)
            {
                continue;
            }
            JSONArray keywords = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            paper.setSource("ICCV");
            StringBuilder sb = new StringBuilder();
            for (Object keyword : keywords)
            {
                sb.append(keyword).append(",");
            }
            paper.setKeyword(sb.toString());
            papers.add(paper);
        }
        return papers;
    }

    //将文件内容转为字符串
    public String readFile(File file)
    {
        RandomAccessFile randomAccessFile = null;
        MappedByteBuffer mappedByteBuffer = null;
        try
        {
            randomAccessFile = new RandomAccessFile(file, "r");
            mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            if (mappedByteBuffer != null)
            {
                return StandardCharsets.UTF_8.decode(mappedByteBuffer).toString();
            }
            else
            {
                return "";
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mappedByteBuffer != null)
            {
                Cleaner cleaner = ((DirectBuffer)mappedByteBuffer).cleaner();
                if (cleaner != null)
                {
                    cleaner.clean();
                }
            }
            if (randomAccessFile != null)
            {
                try
                {
                    randomAccessFile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }










}
