package edu.fzu.paperAnalysis.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName mysqlDB
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/3/25 19:05
 * @Version 1.0
 **/
public class MysqlDB {

    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/paper?useUnicode=true&characterEncoding=utf8";
    private final static String DB_USER = "root";
    private final static String DB_PASSWD = "123456";

    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        MysqlDB mysqlDB=new MysqlDB();
        System.out.println(mysqlDB.getConn());
    }
}

