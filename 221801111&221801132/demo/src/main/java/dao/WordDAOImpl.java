package dao;

import pojo.Paper;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAOImpl implements WordDAO{
    @Override
    public List<Paper> listGetByYear(String str) {
        String sql = "select * from post where title " + "like" +"'%" + str + "%'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<Paper> list = new ArrayList<>();
            while (rs.next()) {
                Paper p = new Paper();
                p.setTitle(rs.getString(1));
                p.setSummary(rs.getString(2));
                p.setLink(rs.getString(3));
                p.setKeyword(rs.getString(4));
                p.setYear(rs.getString(5));
                p.setType(rs.getString(6));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }
}
