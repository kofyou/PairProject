package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;

import entity.items;

//论文的业务逻辑类
public class ItemsDao {

    /**
     * 获取所有的论文信息
     * @return
     */
    public ArrayList<items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<items> list = new ArrayList<items>(); // 论文集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                items item = new items();
                item.setId(rs.getString("id"));
                item.setAbstracts(rs.getString("abstracts"));
                item.setConference(rs.getString("conference"));
                item.setKeyword(rs.getString("keyword"));
                item.setTime(rs.getString("time"));
                item.setTitle(rs.getString("title"));
                item.setLink(rs.getString("link"));
                list.add(item);// 把一个论文加入集合
            }
            return list; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * 根据论文Id获取论文信息
     * @param id
     * @return
     */
    public items getItemsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String idStr = Integer.toString(id);
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist where id=?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idStr);
            rs = stmt.executeQuery();
            if (rs.next()) {
                items item = new items();
                item.setId(rs.getString("id"));
                item.setAbstracts(rs.getString("abstracts"));
                item.setConference(rs.getString("conference"));
                item.setKeyword(rs.getString("keyword"));
                item.setTime(rs.getString("time"));
                item.setTitle(rs.getString("title"));
                item.setLink(rs.getString("link"));
                return item;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    
    /**
     * 根据论文标题检索论文
     * @param title
     * @return
     */
    public ArrayList<items> getItemsByTitle(String title) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<items> list = new ArrayList<items>(); // 论文集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from paperslist where title like ?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            String condition = "%" + title + "%";
            stmt.setString(1, condition);
            rs = stmt.executeQuery();
            while (rs.next()) {
                items item = new items();
                item.setId(rs.getString("id"));
                item.setAbstracts(rs.getString("abstracts"));
                item.setConference(rs.getString("conference"));
                item.setKeyword(rs.getString("keyword"));
                item.setTime(rs.getString("time"));
                item.setTitle(rs.getString("title"));
                item.setLink(rs.getString("link"));
                list.add(item);// 把一个论文加入集合
                
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}
