package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbcutils
{
    public static Connection GetConnection()
    {
        Connection connection=null;
        String username="root";
        String password="ya123456";
        String url="jdbc:mysql://localhost:3306/paperseaching?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url,username,password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return connection;
    }

    public static void CloseConnection(ResultSet resultSet, Statement statement,Connection connection)
    {
        try
        {
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void CloseConnection(Statement statement,Connection connection)
    {
        try
        {
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /*public static void main(String args[])
    {
        System.out.println(Jdbcutils.GetConnection));
    }*/
}
