package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    static String ip = "rm-2zeo875ys8j8pq6epuo.mysql.rds.aliyuncs.com";
    static int port = 3306;
    static String database = "meeting";
    static String encoding = "UTF-8";
    static String loginName = "wzn";
    static String password = "Ww123456";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=GMT", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

    /* 关闭连接的方法 */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection()+"ssss");
    }
}
