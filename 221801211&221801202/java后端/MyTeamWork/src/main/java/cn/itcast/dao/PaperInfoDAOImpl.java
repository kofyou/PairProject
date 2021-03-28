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

    public List<PaperInfo> selectPaperInfos() {
        List<PaperInfo> list = new ArrayList<PaperInfo>();
        String sql = "select * from paperinfo";
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaperInfo paperInfo = new PaperInfo();
                paperInfo.setTitle(resultSet.getString(1));
                paperInfo.setAuthor(resultSet.getString(2));
                paperInfo.setYear(resultSet.getString(3));
                paperInfo.setMeeting(resultSet.getString(4));
                paperInfo.setAbstr(resultSet.getString(5));
                paperInfo.setUrl(resultSet.getString(6));
                paperInfo.setAccesstimes(resultSet.getString(7));
                list.add(paperInfo);
                //将数据存入列表中
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add(PaperInfo paperInfo) {

    }

    public void delete(PaperInfo paperInfo) {

    }

    public PaperInfo getinfo(String title) {
        return null;
    }
}
