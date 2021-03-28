package dao;

import pojo.Keyword;
import pojo.Paper;
import pojo.YearFrequency;
import util.CountWordsUtil;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class KeywordDAOImpl implements KeywordDAO{

    //根据关键词返回包含该关键词的论文列表
    public ArrayList<Paper> keyList(String keyword) {
        ArrayList<Paper> paperList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from paper where keywords like '%" + keyword + "%'";
            rs = stmt.executeQuery(sql);
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
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return paperList;
    }

    //根据关键词、年份返回对应的论文列表
    public ArrayList<Paper> keyList(String keyword, int year) {
        ArrayList<Paper> paperList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from paper where keywords like '%" + keyword + "%'" + " and year='" + year + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                String conference = rs.getString("conference");
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
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return paperList;
    }

    //根据关键词、年份、会议返回对应的论文列表
    public ArrayList<Paper> keyList(String keyword, int year, String conference) {
        ArrayList<Paper> paperList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement("select * from paper where keywords like ? and year=? and conference=?");
            pstm.setString(1, "%" + keyword + "%");
            pstm.setInt(2, year);
            pstm.setString(3, conference);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
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
        } finally {
            DBUtil.close(rs, pstm, conn);
        }
        return paperList;
    }

    public int keyList3(String keyword, int year, String conference) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement("select count(*) from paper where keywords like ? and year=? and conference=?");
            pstm.setString(1, "%" + keyword + "%");
            pstm.setInt(2, year);
            pstm.setString(3, conference);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstm, conn);
        }
        return count;
    }

    //按着频率大小返回频率最高的十个关键词
    public ArrayList<Keyword> GetTop10Keywords() {
        ArrayList<Keyword> keywords = new ArrayList<>();
        StringBuilder words = new StringBuilder();
        String w;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement("select keywords from paper");
            rs = pstm.executeQuery();
            String kw;

            while (rs.next()) {
                kw = rs.getString(1);
                words.append(kw).append(",");
            }

            w = words.toString();
            w = w.substring(0, words.length() - 1);

            keywords = CountWordsUtil.getSortedWordsAndFrequency(w);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstm, conn);
        }
        return keywords;
    }

    //返回十大关键词在该会议每年的频率列表
    public ArrayList<YearFrequency> getKeywordFrequencyEachYear(String conference) {
        ArrayList<YearFrequency> yearFrequencyList = new ArrayList<>();
        ArrayList<Keyword> kwList = GetTop10Keywords();
        YearDAO yearDAO = new YearDAOImpl();
        ArrayList<Integer> years = yearDAO.getYears();
        for (int y:years) {
            YearFrequency yf = new YearFrequency();
            yf.setYear(y);
            ArrayList<Integer> frequency = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                frequency.add(keyList3(kwList.get(i).getName(), y, conference));
            }

            yf.setFrequency(frequency);
            yearFrequencyList.add(yf);
        }

        return yearFrequencyList;
    }

}
