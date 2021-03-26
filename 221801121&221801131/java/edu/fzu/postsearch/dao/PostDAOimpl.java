package edu.fzu.postsearch.dao;

import edu.fzu.postsearch.pojo.Post;
import edu.fzu.postsearch.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PostDAOimpl implements PostDAO{

    @Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from post";
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
    public void add(Post post) {

    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(String title) {

    }

    @Override
    public Post get(String title) {
        return null;
    }

    @Override
    public List<Post> list() {
        List<Post> postList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from post";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                String title = rs.getString("title");
                String keywords = rs.getString("keywords");
                String abs = rs.getString("abstract");
                String link = rs.getString("link");
                String year = rs.getString("year");
                String type = rs.getString("type");
                List<String> kwds= Arrays.asList(keywords.split(","));
                postList.add(new Post(title,kwds,abs,link,year,type));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> listSearch(String search) {
        List<Post> postList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from post where title like \'%%" + search +"%%\'" + "or keywords like \'%%" + search +"%%\'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                String title = rs.getString("title");
                String keywords = rs.getString("keywords");
                String abs = rs.getString("abstract");
                String link = rs.getString("link");
                String year = rs.getString("year");
                String type = rs.getString("type");
                List<String> kwds= Arrays.asList(keywords.split(","));
                postList.add(new Post(title,kwds,abs,link,year,type));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    @Override
    public List<Post> list(int start, int count) {
        return null;
    }


    @Override
    public boolean isExist(String name) {
        return false;
    }
}
