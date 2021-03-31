package cn.itcast.unit;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

public class DBUtil{
    static String driver = "com.mysql.jdbc.Driver";
    static String ip="rm-2zeo875ys8j8pq6epuo.mysql.rds.aliyuncs.com";    //IP名字
    static int port=3306;      //端口
    static String database = "sfjteam";    //数据库名字
    static String encoding = "UTF-8";
    static String loginName = "wzn";
    static String password = "Ww123456";
    static int firSize = 10;    //初始化线程池大小
    static int maxId = 20;      //最大空闲数量
    static int minId = 5;       //最小空闲数量
    static int maxAct = 50;   //最大活跃数
    static int maxWait = 1000;  //最长等待时间

    public static void init(BasicDataSource basicDataSource)
    {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=GMT", ip, port, database, encoding);
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(loginName);
        basicDataSource.setPassword(password);
        basicDataSource.setInitialSize(firSize);
        basicDataSource.setMaxIdle(maxId);
        basicDataSource.setMinIdle(minId);
        basicDataSource.setMaxTotal(maxAct);
        basicDataSource.setMaxWaitMillis(maxWait);
    }

    public Connection getConn(BasicDataSource basicDataSource)
    {
        Connection conn = null;
        init(basicDataSource);
        try{
            conn = basicDataSource.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println("xxx");
    }

    /*public static void main(String[] args) throws SQLException {
        BasicDataSource basicDataSource= new BasicDataSource();
        DBUtil dbUtil = new DBUtil();
        Connection connection = dbUtil.getConn(basicDataSource);
        String sql = "select * from books";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //String title = resultSet.getString(2);
                //double price = resultSet.getDouble(3);
            }
            if(connection !=null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }*/

}
