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


    public void addkey(PaperInfo paperInfo)
    {
        String sql = "select * from name_keyword where name like "+paperInfo.getTitle();
        String str ="";
        try {
            connection = DBUtil1.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                str+=resultSet.getString("keyword");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil1.close(resultSet, preparedStatement, connection);
        }
    }

    public List<PaperInfo> selectPaperInfos() {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sql = "select * from paper";
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
        List<PaperInfo> paperInfos = new ArrayList<PaperInfo>();
        for(PaperInfo paperInfo : list)
        {
            addkey(paperInfo);
            paperInfos.add(paperInfo);
        }/**/
        return paperInfos;
    }

    public void add(PaperInfo paperInfo) {

    }

    public List<PaperInfo> getinfo(String title) {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sx = "'"+title+"%'";
        String sql = "select * from paper where name like " + sx;
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString(1));
                paperInfo.setYear(resultSet.getString(2));
                paperInfo.setMeeting(resultSet.getString(3));
                paperInfo.setAbstr(resultSet.getString(4));
                paperInfo.setUrl(resultSet.getString(5));
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
        String sql = "select * from name_keyword where keyword like " + keyword;
        String name =null;
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil1.close(resultSet,preparedStatement,connection);
        }
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        list = getinfo(name);
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
