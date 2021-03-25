package javar;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperDao {
    
    // 数据库连接
    private static final String URL = 
    "jdbc:mysql://localhost:3306/papersearch?"
    + "useUnicode = true & allowPublicKeyRetrieval=true & characterEncoding = utf-8 "
    + "& useSSL = false & &serverTimezone = GMT";
    // 驱动
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // 用户名
    private static final String USRE = "root";
    // 用户密码
    private static final String PWD = "123456";
    
    /**
     * 获取数据库连接
     * @throws Exception 
     */
    Connection conn = null;
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Connection getConnection() throws Exception {
        if (conn == null) {
            // 1 加载驱动类
            Class.forName(DRIVER);
            // 2 获取数据库连接
            conn = DriverManager.getConnection(URL, USRE, PWD);
        }
        return conn;
    }

    /**
     * 关闭数据库链接
     * @throws Exception
     */
    public void closeConnection() throws Exception {
        pstmt.close();
        conn.close();
        conn = null;
    }

    /**
     * 判断一张表是否存在
     * @param date
     * @return
     * @throws Exception
     */
    Boolean existDateTable(String date) throws Exception {
        ResultSet rs = conn.getMetaData().getTables(null, null, date, null);
        if (rs.next()) {
            closeConnection();
            return true;
        }
        closeConnection();
        return false;
    }
    
    /**
     * 建表
     * @param paperlist
     * @throws Exception
     */
    public  void createPaperTable(String paperlist) throws Exception {
        // 建表
        getConnection();
        sql = "CREATE TABLE `" + paperlist + "` (\r\n" + "  `abstracts` varchar(8191)"
            + " CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n"
            + "  `conference` varchar(255)" 
            + " CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n"
            + "    `keyword` varchar(255)"
            + " CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n"
            + "  `time` varchar(255)"
            + " CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n"
            + "  `title` varchar(255)"
            + " CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n"
            + "  `link` varchar(8191)"
            + " CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL\r\n"
            + ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;";
        pstmt.executeUpdate(sql);

        closeConnection();
        return;
    }
    
    /**
     * 把json数据存储到数据库
     * @param coon
     * @param sta
     * @throws Exception
     */
    public void saveDataToDb(Connection coon,Statistic sta) throws Exception {
        String abs = sta.getAbstract();
        String conf = sta.getConference();
        String keyw = sta.getKeyword();
        String tim = sta.getTime();
        String titl = sta.getTitle();
        String link = sta.getLink();
        String str = "paperlist";
        try {
            getConnection();
            String sql = "INSERT INTO `" + str + "` (`abstracts`, `conference`, `keyword`, `time`, `title`, `link`) VALUES ('" 
                    + abs + "', '" + conf + "', '" + keyw + "', '" + tim + "', '" + titl + "', '" + link + "')";
            
            pstmt = coon.prepareStatement(sql);
            pstmt.executeUpdate(sql);  
        }catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        finally {  
            closeConnection();
        }  
    }
}
