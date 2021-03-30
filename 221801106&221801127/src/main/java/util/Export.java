package util;

import dao.UserDAO;
import dao.UserDAOImpl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Export {//将文件夹的json全部写入数据库
        UserDAO userDAO = new UserDAOImpl();
        int i=1;
        public static List<Map.Entry<String, Integer>> ICCVtenhotwords = new ArrayList<>();
        public static List<Map.Entry<String, Integer>> ECCVtenhotwords = new ArrayList<>();
        public static List<Map.Entry<String, Integer>> CVPRtenhotwords = new ArrayList<>();
        public static List<Map.Entry<String, Integer>> ALLtenHotwords = new ArrayList<>();

        public static HashMap<String,Integer> hotwords = new HashMap<>();   //临时变量
        public static List<Map.Entry<String, Integer>> Hotwords = new ArrayList<>();
        public static HashMap<String,Integer> allhotwords = new HashMap<>();   //临时变量
        public static List<Map.Entry<String, Integer>> allHotwords = new ArrayList<>();

        HashMap<String,Integer> new_hotwords = new HashMap<>();  //从新的文章获取的热词
        public static boolean hasExport = false;

        public void writeICCV() throws IOException, SQLException{
//            Connection connection = DBUtil.getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "delete from papers where number != 100000";
//            statement.execute(sql);
//            System.out.println("论文数据库已清空，现在开始重新读入并统计热词");
//            statement.close();
//            connection.close();


            String path = "E:\\IDEA\\demo\\ICCV";
            File file = new File(path);		//获取其file对象
            File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
            for(File f:fs){					//遍历File[]数组
                if(!f.isDirectory())	{	//若非目录(即文件)，则打印
                    JsonParse1 jsonParse = new JsonParse1(i++,f.toString(),"ICCV");
                    jsonParse.getPaperClass();
                    jsonParse.writeDB();  //这几步获取json数据并且转化为Paper类

                    new_hotwords = BaseUtil.parseHashmap(jsonParse.paper.关键词);
                    BaseUtil.merge(hotwords,new_hotwords);
                    BaseUtil.merge(allhotwords,new_hotwords);
                }
            }
            Hotwords = BaseUtil.sortHashMap(hotwords);
            for (int p = 0;p<10&&p<Hotwords.size();p++){
                ICCVtenhotwords.add(Hotwords.get(p));
            }
            hotwords.clear();
        }


        public void writeECCV() throws IOException, SQLException{
            String path = "E:\\IDEA\\demo\\ECCV";
            File file = new File(path);		//获取其file对象
            File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
            for(File f:fs){					//遍历File[]数组
                if(!f.isDirectory())	{	//若非目录(即文件)，则打印
                    JsonParse jsonParse = new JsonParse(i++,f.toString());
                    jsonParse.testPaper();
                    jsonParse.writeDB();  //这几步获取json数据并且转化为Paper类
                    new_hotwords = BaseUtil.parseHashmap(jsonParse.paper.关键词);
                    BaseUtil.merge(hotwords,new_hotwords);
                    BaseUtil.merge(allhotwords,new_hotwords);
                }
            }
            Hotwords = BaseUtil.sortHashMap(hotwords);
            for (int p = 0;p<10&&p<Hotwords.size();p++){
                ECCVtenhotwords.add(Hotwords.get(p));
            }

            hotwords.clear();
        }


        public void writeCVPR()throws IOException, SQLException{
            String path = "E:\\IDEA\\demo\\CVPR";
            File file = new File(path);		//获取其file对象
            File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
            for(File f:fs){					//遍历File[]数组
                if(!f.isDirectory())	{	//若非目录(即文件)，则打印
                    JsonParse1 jsonParse = new JsonParse1(i++,f.toString(),"CVPR");
                    jsonParse.getPaperClass();
                    jsonParse.writeDB();  //这几步获取json数据并且转化为Paper类
                    new_hotwords = BaseUtil.parseHashmap(jsonParse.paper.关键词);
                    BaseUtil.merge(hotwords,new_hotwords);
                    BaseUtil.merge(allhotwords,new_hotwords);
                }
            }
            Hotwords = BaseUtil.sortHashMap(hotwords);
            for (int p = 0;p<10&&p<Hotwords.size();p++){
                CVPRtenhotwords.add(Hotwords.get(p));
            }
        }


    public void writeAllJsonIntoDB() throws IOException, SQLException {  //将文件夹中所有的json写入数据库并统计热词云写入数据库（会先删掉数据库里面旧的论文）
            writeICCV();
            writeECCV();
            writeCVPR();
            allHotwords = BaseUtil.sortHashMap(allhotwords);
            for (int p = 0;p<10&&p<allHotwords.size();p++){
                ALLtenHotwords.add(allHotwords.get(p));
            }

            userDAO.writeTenhotwordsIntoDB(ICCVtenhotwords,"ICCV");
            userDAO.writeTenhotwordsIntoDB(ECCVtenhotwords,"ECCV");
            userDAO.writeTenhotwordsIntoDB(CVPRtenhotwords,"CVPR");
            userDAO.writeTenhotwordsIntoDB(ALLtenHotwords,"ALL");
            hasExport = true;
        }

            public static void main(String[] args) throws SQLException, IOException {
                Export export = new Export();
                export.writeAllJsonIntoDB();

                BaseUtil.showList(ICCVtenhotwords);
                System.out.println("+++++++++++++++++++");
                BaseUtil.showList(ECCVtenhotwords);
                System.out.println("+++++++++++++++++++");
                BaseUtil.showList(CVPRtenhotwords);
                System.out.println("+++++++++++++++++++");
                BaseUtil.showList(ALLtenHotwords);
            }
}
