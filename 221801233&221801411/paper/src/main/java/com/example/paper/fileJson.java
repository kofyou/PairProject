package com.example.paper;

import com.google.gson.Gson;
import dao.UserDAO;
import dao.UserDAOImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import pojo.User;
import util.DBUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileJson {
    HashMap<String,String> hashMap;
    public fileJson()
    {
        //hashMap.put("")
    }
    public void saveDataToFile(String fileName,String data) {
        BufferedWriter writer = null;
        File file = new File("d:\\"+ fileName + ".json");
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功！");
    }
    public String getDatafromFile(String fileName) {

        String Path="paperinfo/ICCV/" + fileName;
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;

    }
    public String replacestr(String str)
    {
           return null;

    }
    public void jsontosql()
    {
        File file = new File("paperinfo/CVPR");
        String[] files = file.list();
        List<User> list = new ArrayList<User>();

        for(String path:files) {
            String str=getDatafromFile(path);
            User user = new User();
            JSONObject jsonObject = JSONObject.fromObject(str);
            //System.out.println(str);
            if (jsonObject != null) {
                user.setTitle(jsonObject.getString("论文名称"));
                //System.out.println(jsonObject.getString("论文名称"));
                user.setDigest(jsonObject.getString("摘要"));
                user.setLink(jsonObject.getString("原文链接"));
                user.setTime(jsonObject.getString("发布时间"));
                user.setYear(jsonObject.getString("会议和年份"));
                //取出按钮权限的数据
                JSONArray jsonArray = jsonObject.getJSONArray("关键词");
                //System.out.println(jsonArray.size());
                String value = "";
                //jsonArray.size()  json数据的大小(长度)
                //遍历jsonArray 转换格式,放入map集合中
                for (int i = 0; i < jsonArray.size(); i++) {
                    value += jsonArray.getString(i);
                    System.out.println("已执行:" + i);
                }
                user.setKey(value);
            }
            UserDAO userDAO = new UserDAOImpl();
            userDAO.add(user);
        }

    }
    public void comjsontosql(String pathname)
    {

        File file = new File("paperinfo/"+pathname);
        String[] files = file.list();
        List<User> list = new ArrayList<User>();
        for(String path:files)
        {
            String keywords = "";
            String jsonString = getDatafromFile(path);
            JSONObject jsonObject = JSONObject.fromObject(jsonString.substring(0, jsonString.length() - 1));
            User user=new User();
            if (jsonObject != null) {
                System.out.println(path+"/n");
                if(jsonObject.get("abstract")!=null)
                user.setDigest(jsonObject.get("abstract").toString());
                user.setYear("ICCV");
                user.setTime(jsonObject.getString("chronOrPublicationDate"));
                JSONArray jsonArray = jsonObject.getJSONArray("keywords");//.getJSONObject(0).getJSONArray("kwd");
                for (int i = 0; i < jsonArray.size(); i++) {
                    keywords += jsonArray.getString(i) + ",";
                }
                user.setKey(keywords.substring(0, keywords.length() - 1));
                user.setLink(jsonObject.getString("doiLink"));
                user.setTitle(jsonObject.getString("title"));
                UserDAO userDAO = new UserDAOImpl();
                userDAO.add(user);
            }
        }
    }
    public void write(){
        StringBuilder stringBuilder=new StringBuilder();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select link from paper ";
            ResultSet rs = s.executeQuery(sql);
            int i=0;
            while (rs.next()) {
                stringBuilder.append(rs.getString("link") + "\n");
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
                BufferedWriter writer = null;
                File file = new File("out.txt");
                if(!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
                    writer.write(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(writer != null){
                            writer.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("文件写入成功！");



            }



     public static void main(String [] args)
    {
        fileJson file=new fileJson();
        //file.jsontosql();
        //file.comjsontosql("CVPR");
        file.write();
        //System.out.println(file.getDatafromFile("myidea\\paperinfo\\ECCV\\2016_(ell ^{0})-Sparse Subspace Clustering"));

    }
}
