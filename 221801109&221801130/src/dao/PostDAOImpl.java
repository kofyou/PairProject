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
    public void update(Post user) {

    }

    @Override
    public void add(Post bean) {

        String sql = "insert into user values(null ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getContent());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
              //  bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
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
    public Post get(int id) {
       

        
        return null;
    }

	@Override
    public List<Post> list() {

	    String sql = "select * from post";
	    List<Post> postList = new ArrayList<>();
	   // int i = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                /*if (i < 10) {
                    System.out.println(title);
                    i++;
                }*/
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

	@Override
    public List<Post> list(int start, int count) {
       return null;
    }

    public List<Post> list(String text) {
       // String sql = "select * from post where title = '"+text+"' LIMIT #{start}, #{count}";
        String sql = "select * from post where title like '%" + text + "%' or keyword like '%" + text + "%' ";
        List<Post> postList = new ArrayList<>();
        // int i = 0;
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
	@Override
    public boolean isExist(String name) {
        Post user = get(name);
        return user != null;

    }

	@Override
    public Post get(String name) {
        return null;
    }

    public Post get(String name, String password) {
       return null;
    }

}