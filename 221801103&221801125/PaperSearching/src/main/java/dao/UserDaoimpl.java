package dao;

import pojo.User;
import utils.Jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDaoimpl
{


    public void UpdateUserInfo(User infos)
    {
        try
        {
            Connection connection= Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "update user set username=?,company=?,address=?,info=?  where account=?");
            preparedStatement.setString(1,infos.getUsername());
            preparedStatement.setString(2,infos.getCompany());
            preparedStatement.setString(3,infos.getAddress());
            preparedStatement.setString(4,infos.getInfo());
            preparedStatement.setString(5,infos.getAccount());
            preparedStatement.execute();

            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void InsertUser(String account, String password)
    {
        try
        {
            Connection connection= Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "insert into user values(?,?,?,?,?,?)");
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,"");
            preparedStatement.setString(4,"");
            preparedStatement.setString(5,"");
            preparedStatement.setString(6,"");

            preparedStatement.execute();

            Jdbcutils.CloseConnection(preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Boolean IsUserExist(String account)
    {
        int n=0;
        try
        {
            Connection connection= Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "select * from user where account=?");
            preparedStatement.setString(1,account);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next())
            {
                n++;
            }
            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(n==0)
        {
            return false;
        }

        return true;
    }

    public User GetUser(String account)
    {
        User user=new User();
        try
        {
            Connection connection= Jdbcutils.GetConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "select * from user where account=?");
            preparedStatement.setString(1,account);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next())
            {
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setAddress(resultSet.getString("address"));
                user.setCompany(resultSet.getString("company"));
                user.setInfo(resultSet.getString("info"));
            }

            Jdbcutils.CloseConnection(resultSet,preparedStatement,connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

       return user;
    }

    /*public static void main(String args[])
    {
        String[] str = {"123","2","3","","fd"};
        User user=new UserDaoimpl().GetUser("123");
        new UserDaoimpl().UpdateUserInfo(str);
        System.out.println(user.toString());
    }*/
}
