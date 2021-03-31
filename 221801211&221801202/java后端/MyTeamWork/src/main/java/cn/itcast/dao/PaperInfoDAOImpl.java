package cn.itcast.dao;

import cn.itcast.domain.Keynum;
import cn.itcast.domain.PaperInfo;
import cn.itcast.unit.DBUtil1;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperInfoDAOImpl implements PaperInfoDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    DBUtil1 dbUtil;
    BasicDataSource basicDataSource;
    ResultSet resultSet;

    public String addwordkey(PaperInfo paperInfo,String str) {
        String sql = "select * from name_keyword where name = \"" + str + "\"";
        String temp = "";
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                temp += (resultSet.getString("keyword") + ",");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public PaperInfo selectPaperInfo(String str) {      //展示单个页面
        String sql = "select * from paper where name = \"" + str + "\"";
        PaperInfo paperInfo = new PaperInfo();
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paperInfo.setTitle(resultSet.getString("name"));
                paperInfo.setYear(resultSet.getString("year"));
                paperInfo.setMeeting(resultSet.getString("meeting"));
                paperInfo.setAbstr(resultSet.getString("abstract"));
                paperInfo.setUrl(resultSet.getString("url"));
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil1.close(resultSet,preparedStatement,connection);
        }
        String string = addwordkey(paperInfo,str);
        paperInfo.setKeyword(string);
        return paperInfo;
    }

    public List<PaperInfo> selectPaperInfos() {     //展示列表
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sql = "select * from paper"+ " limit 1000";
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString("name"));
                paperInfo.setYear(resultSet.getString("year"));
                paperInfo.setMeeting(resultSet.getString("meeting"));
                paperInfo.setAbstr(resultSet.getString("abstract"));
                paperInfo.setUrl(resultSet.getString("url"));
                paperInfo.setKeyword("hidden...");
                list.add(paperInfo);
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil1.close(resultSet,preparedStatement,connection);
        }
        return list;
    }

    public void add(PaperInfo paperInfo) {

    }

    public List<PaperInfo> getinfo(String title) {  //模糊查询
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sx = "'"+title+"%'";
        String sql = "select * from paper where name like " + sx;
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString("name"));
                paperInfo.setYear(resultSet.getString("year"));
                paperInfo.setMeeting(resultSet.getString("meeting"));
                paperInfo.setAbstr(resultSet.getString("abstract"));
                paperInfo.setUrl(resultSet.getString("url"));
                paperInfo.setKeyword("hidden...");
                list.add(paperInfo);
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil1.close(resultSet,preparedStatement,connection);
        }
        return list;
    }

    public List<PaperInfo> getkey(String keyword) {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sql = "select * from paper,name_keyword where name_keyword.keyword = \"" + keyword + "\"" + "and paper.name = name_keyword.name";
        String name =null;
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString("name"));
                paperInfo.setYear(resultSet.getString("year"));
                paperInfo.setMeeting(resultSet.getString("meeting"));
                paperInfo.setAbstr(resultSet.getString("abstract"));
                paperInfo.setUrl(resultSet.getString("url"));
                paperInfo.setKeyword(resultSet.getString("keyword"));
                list.add(paperInfo);
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil1.close(resultSet,preparedStatement,connection);
        }
        return list;
    }

    public void delete(String title) {
        String sql = "DELETE FROM paper WHERE name =\""+title+"\"";
        try {
            connection = DBUtil1.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil1.close(resultSet,preparedStatement,connection);
        }
    }
}
