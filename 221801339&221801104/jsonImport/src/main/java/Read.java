import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
public class Read {
    public static void main(String args[]) throws IOException {
        String name = "";
        String title = "";
        String date = "";
        String article = "";
        String keyWords = "";
        File f = new File("F:/tmp/jsonImport/src/main/resources/output.txt");//输出文件
        File path=new File("F:/tmp/jsonImport");//json文件路径
        List<String> list = new ArrayList<String>();
        list = getFileList(path);//json文件列表
        FileOutputStream o = null;
        byte[] buff = new byte[]{};
        JsonParser parse = new JsonParser();  //创建json解析器
        for (String l : list) {
             try {
                 //json文件内格式一定要正确 否则读取不出来 不能有;号
                JsonObject object = (JsonObject) parse.parse(new FileReader(new File(l))); //创建jsonObject对象
                //解析作者
                JsonArray array = object.get("authors").getAsJsonArray();
                for (int i = 0; i < array.size(); i++) {
                    JsonObject subObject = array.get(i).getAsJsonObject();
                    name += subObject.get("name").getAsString();
                }
                //解析关键字
                JsonArray keywords = object.get("keywords").getAsJsonArray();
                for (int i = 0; i < keywords.size(); i++) {
                    JsonObject subObject = keywords.get(i).getAsJsonObject();
                    JsonArray kwd = subObject.get("kwd").getAsJsonArray();
                    for (int j = 0; j < kwd.size(); j++) {
                        String tmpString = kwd.get(j).getAsString();
                        keyWords += tmpString + ",";
                    }
                }
                //解析标题
                title = object.get("publicationTitle").getAsString();
                //解析文章
                article = object.get("abstract").getAsString();
                //解析时间
                date = object.get("conferenceDate").getAsString();

                if (f.exists())
                    System.out.println("输出成功");
                else {
                    System.out.println("新建并输出");
                    f.createNewFile();
                }
                String str = title + "\n" + name + "\n" + article + "\n" + date + "\n" + keyWords + "\n";
                buff = str.getBytes();
                o = new FileOutputStream(f, true);//继续写入
                o.write(buff);
                o.flush();
                o.close();
            } catch(JsonIOException e){
                e.printStackTrace();
            } catch(JsonSyntaxException e){
                e.printStackTrace();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public static List<String> getFileList(File file) {

        List<String> result = new ArrayList<String>();

        if (!file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            result.add(file.getAbsolutePath());
        } else {
            File[] directoryList = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().indexOf("json") > -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for (int i = 0; i < directoryList.length; i++) {
                result.add(directoryList[i].getPath());
            }
        }

        return result;
    }
}
