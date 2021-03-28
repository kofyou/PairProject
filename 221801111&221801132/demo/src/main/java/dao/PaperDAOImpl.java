package dao;

import pojo.Paper;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperDAOImpl implements PaperDAO {

    List<Paper> list = new ArrayList<>();
    Paper paper = null;
    @Override
    public Paper getByTitle(String str) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from post where title = " + str;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                paper.setTitle(rs.getString(1));
                paper.setSummary(rs.getString(2));
                paper.setLink(rs.getString(3));
                paper.setKeyword(rs.getString(4));
                paper.setYear(rs.getString(5));
                paper.setType(rs.getString(6));
                list.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paper;
    }


    @Override
    public Paper getByKeyword(String str) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from post where keyword = " + str;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                paper.setTitle(rs.getString(1));
                paper.setSummary(rs.getString(2));
                paper.setLink(rs.getString(3));
                paper.setKeyword(rs.getString(4));
                paper.setYear(rs.getString(5));
                paper.setType(rs.getString(6));
                list.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paper;
    }

    @Override
    public Paper getByType(String str) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from post where type = " + str;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                paper.setTitle(rs.getString(1));
                paper.setSummary(rs.getString(2));
                paper.setLink(rs.getString(3));
                paper.setKeyword(rs.getString(4));
                paper.setYear(rs.getString(5));
                paper.setType(rs.getString(6));
                list.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paper;
    }
}
