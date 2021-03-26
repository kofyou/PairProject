package dao;

import pojo.Paper;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaperDAOImpl implements PaperDAO{
    public int getTotal() {
        return 0;
    }

    public void add(Paper paper) {
    }

    public void update(Paper paper) {

    }

    public void delete(int id) {

    }

    public ArrayList<Paper> list(String str) {
        ArrayList<Paper> paperList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from paper where title like '%" + str + "%' or summary like '%"
                    + str + "%' or keywords like '%" + str + "%'" ;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                String conference = rs.getString("conference");
                int year = rs.getInt("year");
                String summary = rs.getString("summary");
                String link = rs.getString("link");
                String keywords = rs.getString("keywords");

                Paper paper = new Paper();
                paper.setTitle(title);
                paper.setConference(conference);
                paper.setYear(year);
                paper.setSummary(summary);
                paper.setLink(link);
                paper.setKeywords(keywords);
                paperList.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paperList;
    }

    public List<Paper> list(int start, int count) {
        return null;
    }

    public boolean isExist(String str) {
        return false;
    }

    public Paper get(String title) {
        return null;
    }
}
