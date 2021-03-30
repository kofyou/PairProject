package dao;

import pojo.Post;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {

	@Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from User";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }


	@Override
    public void delete(String t) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from post where title='"+t+"'";
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
    }

	@Override
    public List<Post> list() {
	    String sql = "select * from post";
	    List<Post> postList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String link = rs.getString("link");
                String keyWord = rs.getString("keyWord");
                String time = rs.getString("time");
                String platform = rs.getString("platform");
                postList.add(new Post(title, content, link, keyWord, time, platform));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> list(String text) {
        String sql = "select * from post where title like '%" + text + "%' or keyword like '%" + text + "%' ";
        List<Post> postList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String link = rs.getString("link");
                String keyWord = rs.getString("keyWord");
                String time = rs.getString("time");
                String platform = rs.getString("platform");
                postList.add(new Post(title, content, link, keyWord, time, platform));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

}