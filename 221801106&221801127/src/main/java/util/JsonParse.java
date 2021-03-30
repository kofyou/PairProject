package util;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import baseclass.Paper;
import baseclass.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;


public class JsonParse {//将json文件写入数据库
       Paper paper;
       JSONObject jsonObject;
       String url;
       int number;

       public JsonParse(int number,String url){
              this.number = number;
              this.url = url;
       }

       public void testPaper() throws IOException {  //获取paper类
              paper = getBean(Paper.class);
           //   System.out.println(paper);
       }

       private <T> T getBean(Class<T> t) throws IOException {
              File file = new File(url);
              String jsonString = FileUtils.readFileToString(file);
              jsonObject = JSON.parseObject(jsonString);

              return JSON.parseObject(jsonString,t);
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
              Statement statement = connection.createStatement();
              String sql = String.format("insert into papers values (%d,'%s','%s','%s','%s','%s','%s','%s')",number,paper.摘要,temp[0],temp[1],keywords,paper.发布时间,paper.论文名称,paper.原文链接);

              statement.execute(sql);

              statement.close();
              connection.close();
       }

       public static void main(String[] args){
              String a = new String("jdfhjdf 'jdfhdf' djfhd");
              a = a.replaceAll("'","\'");
              System.out.println(a);
       }
}
