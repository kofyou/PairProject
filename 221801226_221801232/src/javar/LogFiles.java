package javar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.TreeSet;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSONReader;


public class LogFiles {
    TreeSet<File> files;
    PaperDao dao = new PaperDao();
    
    /**
     * 根据数据存储路径读到文件集合
     * @param path 数据存储路径
     * @throws FileNotFoundException 
     */
    public LogFiles(String path) throws FileNotFoundException {
        File logFile = new File(path);
        File[] temp = logFile.listFiles();
        files = new TreeSet<File>();
        for (int i = 0; i < temp.length; ++i) {
            files.add(temp[i]);
        }
        readFiles();
    }
    
    /**
     * 从文件集合获得每个json文件路径进行解析
     * @param sta
     * @throws FileNotFoundException 
     */
    public void readFiles() throws FileNotFoundException {
        String path;
        for (File f :files) {
            path = f.getAbsolutePath();
            System.out.println(path);
            readECCVFileContent(path);
        }
    }
 
    /**
             * 把json解析到自定义类中
     * @param path
     * @param sta
     * @throws FileNotFoundException 
     */
    public void readECCVFileContent(String path) throws FileNotFoundException {
        
        String key = "";
        Statistic sta = new Statistic();
        
        JSONReader reader=new JSONReader(new FileReader(path));
        //开始解析最外层的对象
        reader.startObject();
        
        //遍历最外层的对象
        while (reader.hasNext()){
            String longkeyword = "";
            key = reader.readString(); //读取键的值
            if(key.equals("摘要"))
            {
                String abstracts = reader.readObject().toString();
                sta.setAbstact(abstracts);

            }
            else if(key.equals("会议和年份"))
            {
                String conference = reader.readObject().toString();
                sta.setConference(conference);

            }
            else if(key.equals("关键词"))
            {
                reader.startArray();//开始解析数组
                while (reader.hasNext())//遍历数组
                {
                    String keyword = reader.readString();
                    longkeyword += keyword;
                    longkeyword += ",";
                    
                }
                sta.setKeyword(longkeyword);
                reader.endArray();
            }
            else if(key.equals("发布时间"))
            {
                String time = reader.readObject().toString();
                sta.setTime(time);
            }
            else if(key.equals("论文名称"))
            {
                String title = reader.readObject().toString();
                sta.setTitle(title);

            }
            else if(key.equals("原文链接"))
            {
                String link = reader.readObject().toString();
                //System.out.println("原文链接为：" + link);
                sta.setLink(link);
            }
            
            
            
        }
        try {
            Connection conn = dao.getConnection();
            dao.saveDataToDb(conn,sta);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reader.endObject();
        
        
        
    }
}
