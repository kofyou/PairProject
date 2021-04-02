package util;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import baseclass.Paper;
import baseclass.User;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;


public class JsonParse1 {//将其他两种类型的json文件写入数据库
    Paper paper;
    String url;
    int number;
    String meetingType;

    public JsonParse1(int number,String url,String meetingType){
        this.number = number;
        this.url = url;
        this.meetingType = meetingType;
    }

    public void getPaperClass() throws IOException , NullPointerException{
        paper = new Paper();
        List<String> keywords = new ArrayList<>();
        File file = new File(url);
        String jsonString = FileUtils.readFileToString(file);
        JSONObject jsonObject = JSONObject.fromObject(jsonString.substring(0, jsonString.length() - 1));
        paper.ID = number;
        paper.会议和年份 = meetingType+" "+jsonObject.getString("chronOrPublicationDate");
        paper.发布时间 = jsonObject.getString("chronOrPublicationDate");
        if (jsonObject.get("abstract")!=null)
            paper.摘要 = jsonObject.get("abstract").toString();
        else paper.摘要 = "null";

        if (0!=jsonObject.get("keywords").toString().length()){
            JSONArray jsonArray = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            for (int i = 0;i < jsonArray.size(); i++){
                keywords.add(jsonArray.get(i).toString());
            }
            String[] keywordss = new String[keywords.size()];
            for (int i = 0;i < keywords.size();i++){
                keywordss[i] = keywords.get(i);
            }
            paper.关键词 = keywordss;
        }
        else{
            String temp = jsonObject.get("keywords").toString();
            String[] temp1 = new String[1];
            temp1[0] = temp;
            paper.关键词 = temp1;
        }

        paper.原文链接 = jsonObject.getString("doiLink");
        paper.论文名称 = jsonObject.getString("title");

    }

    public void writeDB() throws SQLException {   //将文件读入的paper类写入数据库
        String temp[] = paper.会议和年份.split(" ");
        String temp1[] = paper.关键词;
        StringBuffer stringBuffer = new StringBuffer();
        for (String keyword : temp1){
            stringBuffer.append(keyword+",");
        }
        String keywords = stringBuffer.toString();
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into papers values (?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,Integer.toString(number));
        preparedStatement.setString(2,paper.摘要);
        preparedStatement.setString(3,temp[0]);
        preparedStatement.setString(4,temp[1]);
        preparedStatement.setString(5,keywords);
        preparedStatement.setString(6,paper.发布时间);
        preparedStatement.setString(7,paper.论文名称);
        preparedStatement.setString(8,paper.原文链接);

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    public static void main(String[] args){
        JsonParse1 a = new JsonParse1(2,"C:\\Users\\29847\\Desktop\\test.json","CCTV");
        try {
            a.getPaperClass();
            a.writeDB();
        }catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
