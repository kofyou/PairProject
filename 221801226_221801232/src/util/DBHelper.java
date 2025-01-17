package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
   
    private static final String driver = "com.mysql.cj.jdbc.Driver"; //数据库驱动
    //连接数据库的URL地址
    private static final String url = 
            "jdbc:mysql://localhost:3306/papersearch?"
            + "useUnicode = true & allowPublicKeyRetrieval=true & characterEncoding = utf-8 "
            + "& useSSL = false & &serverTimezone = GMT";
    private static final String username="root";//数据库的用户名
    private static final String password="123456";//数据库的密码
    
    private static Connection conn=null;
    
    //静态代码块负责加载驱动
    static 
    {
        try
        {
            Class.forName(driver);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    //单例模式返回数据库连接对象
    public static Connection getConnection() throws Exception
    {
        if(conn==null)
        {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }
    
    public static void main(String[] args) {
        
        try
        {
           Connection conn = DBHelper.getConnection();
           if(conn!=null)
           {
               System.out.println("数据库连接正常！");
           }
           else
           {
               System.out.println("数据库连接异常！");
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
}
