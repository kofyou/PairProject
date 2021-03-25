package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TreeSet;

import com.alibaba.fastjson.JSONReader;

import entity.items;

public class GetDataToDB {
    TreeSet<File> files;
    private int paperNum = 1;
    
    /**
     * 根据数据存储路径读到文件集合
     * @param path 数据存储路径
     * @throws FileNotFoundException 
     */
    public GetDataToDB(String path){
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
    public void readFiles(){
        String path;
        for (File f :files) {
            path = f.getAbsolutePath();
            System.out.println(path);
            try {
                readECCVFileContent(path);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
        items item = new items();
        
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
                item.setAbstracts(abstracts);

            }
            else if(key.equals("会议和年份"))
            {
                String conference = reader.readObject().toString();
                item.setConference(conference);

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
                item.setKeyword(longkeyword);
                reader.endArray();
            }
            else if(key.equals("发布时间"))
            {
                String time = reader.readObject().toString();
                item.setTime(time);
            }
            else if(key.equals("论文名称"))
            {
                String title = reader.readObject().toString();
                System.out.println("论文：" + title);
                item.setTitle(title);

            }
            else if(key.equals("原文链接"))
            {
                String link = reader.readObject().toString();
                
                item.setLink(link);
            }
            
        }
        try {
            Connection conn = DBHelper.getConnection();
            saveDataToDb(conn,item);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reader.endObject();
        
    }
    
    /**
     * 把json数据存储到数据库
     * @param coon
     * @param sta
     * @throws Exception
     */
    public void saveDataToDb(Connection coon,items item) throws Exception {
        String abs = item.getAbstracts();
        String conf = item.getConference();
        String keyw = item.getKeyword();
        String tim = item.getTime();
        String titl = item.getTitle();
        String link = item.getLink();
        String str = "paperslistTest";
        String papNum = Integer.toString(paperNum);
        
        PreparedStatement pstmt = null;
        
        try {
            String sql = "INSERT INTO `" + str + "` (`id`,`abstracts`, `conference`, `keyword`, `time`, `title`, `link`) "
                    + "VALUES ('" 
                    + papNum + "', '" + abs + "', '" + conf + "', '" + keyw + "', '" + tim + "', '" + titl + "', '" + link + "')";
            
            pstmt = coon.prepareStatement(sql);
            pstmt.executeUpdate(sql);  
            paperNum++;
        }catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        finally {  
            // 释放语句对象
            if (pstmt != null) {
                try {
                    pstmt.close();
                    pstmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
         }
        }  
    }
}
