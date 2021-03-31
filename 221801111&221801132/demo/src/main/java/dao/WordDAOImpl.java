package dao;

import pojo.KeyWord;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAOImpl implements WordDAO{
    @Override
    public List<KeyWord> listGetByYear2012CVPR() {
        String sql = "select keyword from post where year = '2012' and type = 'CVPR'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<KeyWord> list = new ArrayList<>();
            while (rs.next()) {
                KeyWord w = new KeyWord();
                w.setWord(rs.getString(1));
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

    @Override
    public List<KeyWord> listGetByYearCVPR(String str) {
        if (Integer.toString(2001).equals(str) || Integer.toString(2002).equals(str)) return null;
        else {
            String sql = "select keyword from post where year = '" + str + "' AND type = 'CVPR'";
            try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery(sql);
                List<KeyWord> list = new ArrayList<>();
                while (rs.next()) {
                    KeyWord w = new KeyWord();
                    w.setWord(rs.getString(1));
                    w.setYear(str);
                    list.add(w);
                }
            /*for (KeyWord word : list) {
                if (word.getYear().equals(Integer.toString(2001)))
                    System.out.println(word.getWord());
            }*/
                return list;
            } catch (SQLException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<KeyWord> listGetByYearICCV(String str) {
        String sql = "select keyword from post where year = '" + str + "' and type = 'ICCV'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<KeyWord> list = new ArrayList<>();
            while (rs.next()) {
                KeyWord w = new KeyWord();
                w.setWord(rs.getString(1));
                w.setYear(str);
                list.add(w);
            }
            return list;
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KeyWord> listGetByYearECCV(String str) {
        String sql = "select keyword from post where year = '" + str + "' and type = 'ECCV'";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery(sql);
            List<KeyWord> list = new ArrayList<>();
            while (rs.next()) {
                KeyWord w = new KeyWord();
                w.setWord(rs.getString(1));
                w.setYear(str);
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
