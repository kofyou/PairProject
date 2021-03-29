package cn.itcast.dao;

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

    public List<PaperInfo> selectPaperInfos() {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sql = "select * from paperinfo";
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString("title"));
                paperInfo.setAuthor(resultSet.getString("authors"));
                paperInfo.setYear(resultSet.getString("chronDate"));
                paperInfo.setMeeting(resultSet.getString("meeting"));
                paperInfo.setAbstr(resultSet.getString("abstract"));
                paperInfo.setUrl(resultSet.getString("url"));
                paperInfo.setAccesstimes(resultSet.getString("accesstimes"));
                paperInfo.setKeyword(resultSet.getString("keywords"));
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

    public List<PaperInfo> getinfo(String title) {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sx = "'"+title+"%'";
        String sql = "select * from paperinfo where title like " + sx;
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString(1));
                paperInfo.setAuthor(resultSet.getString(2));
                paperInfo.setYear(resultSet.getString(3));
                paperInfo.setMeeting(resultSet.getString(4));
                paperInfo.setAbstr(resultSet.getString(5));
                paperInfo.setUrl(resultSet.getString(6));
                paperInfo.setAccesstimes(resultSet.getString(7));
                paperInfo.setKeyword(resultSet.getString(8));
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

    public List<PaperInfo> getkey(String title) {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sx = "'"+title+"%'";
        String sql = "select * from paperinfo where keywords like " + sx;
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString(1));
                paperInfo.setAuthor(resultSet.getString(2));
                paperInfo.setYear(resultSet.getString(3));
                paperInfo.setMeeting(resultSet.getString(4));
                paperInfo.setAbstr(resultSet.getString(5));
                paperInfo.setUrl(resultSet.getString(6));
                paperInfo.setAccesstimes(resultSet.getString(7));
                paperInfo.setKeyword(resultSet.getString(8));
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
        String sql = "DELETE FROM paperinfo WHERE title=\""+title+"\"";
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
