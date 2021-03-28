package dao;

import pojo.Paper;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperDAOImpl implements PaperDAO {

    @Override
    public List<Paper> listGetByTitle(String str) {
        String sql = "select * from post where title = '" + str + "'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<Paper> list = new ArrayList<Paper>();
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

    @Override
    public List<Paper> listGetByKeyword(String str) {
        String sql = "select * from post where keyword = '"+ str +"'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<Paper> list = new ArrayList<Paper>();
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

    @Override
    public List<Paper> listGetByType(String str) {
        String sql = "select * from post where type = '"+ str +"'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<Paper> list = new ArrayList<Paper>();
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
