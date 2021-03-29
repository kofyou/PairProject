package dao;

import pojo.Paper;
import pojo.Word;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAOImpl implements WordDAO{
    @Override
    public List<Word> listGetByYear() {
        String sql = "select keyword from post where year = '2012' and type = 'CVPR'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<Word> list = new ArrayList<>();
            while (rs.next()) {
                Word w = new Word();
                w.setKeywords(rs.getString(1));
                w.setYear("2012");
                list.add(w);
            }
            return list;
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }
}
