package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.unit.DBUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    DBUtil dbUtil;
    BasicDataSource basicDataSource;

    public List<User> selectUsers(){
        List<User> list = new ArrayList<User>();
        String sql = "select * from books";
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //输出
            }
            if(connection != null){
                connection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add(User user) {

    }

    public void delete(User user) {

    }

    public User getinfo(int id) {
        return null;
    }
}
