package dao;


import pojo.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

	@Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from paper";
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
    public void add(User bean) {

        String sql = "insert into paper value(null,? ,? ,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            //String sql="insert into paper(title,digest) values('"+bean.getTitle()+"','"+bean.getDigest()+"')" ;
            ///System.out.println(bean.getTime());
            //执行SQL
            //Statement state = c.createStatement();
            //state.executeUpdate(sql);

            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getDigest());
            ps.setString(3,bean.getYear());
            ps.setString(4,bean.getKey());
            ps.setString(5, bean.getTime());
            ps.setString(6, bean.getLink());
            ps.execute();
            //System.out.println("1");
            //System.out.println(result);
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

	@Override
    public void update(User bean) {
        

    }

	@Override
    public boolean delete(int id) {
        boolean returnbool=false;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from paper where id="+id;
            int returnw= s.executeUpdate(sql);
            if(returnw==1)
                returnbool=true;
            else{
                returnbool=false;
            }
        }
             catch (SQLException e) {
                e.printStackTrace();
            }
       return returnbool;
    }
	
	@Override
    public User get(int id) {
	    return null;
    }

	@Override
    public List<User> list(int cur,int span) {
        List<User> l  = new ArrayList<User>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from paper";
            ResultSet rs = s.executeQuery(sql);
            int i=0;
            while (rs.next()&&i<span+cur) {
                i++;
                if(i>=cur) {
                    String title = rs.getString("title");
                    String digest = rs.getString("digest");
                    String key = rs.getString("key");
                    String year = rs.getString("year");
                    String time = rs.getString("time");
                    String link = rs.getString("link");
                    int id = rs.getInt("id");
                    User user = new User();
                    user.setTitle(title);
                    user.setYear(year);
                    user.setTime(time);
                    user.setKey(key);
                    user.setDigest(digest);
                    user.setLink(link);
                    user.setId(id);
                    l.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;

    }

	@Override
    public List<User> list(int start) {
       return null;
    }

	@Override
    public boolean isExist(String name) {
        User user = get(name);
        return user != null;

    }

	@Override
    public User get(String name) {
        return null;
    }

    public User get(String name, String password) {
       return null;
    }

}