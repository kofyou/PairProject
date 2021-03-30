package edu.fzu.paperAnalysis.dao;

import edu.fzu.paperAnalysis.db.MysqlDB;
import edu.fzu.paperAnalysis.vo.Paper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaperDaoImpl
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/3/25 19:13
 * @Version 1.0
 **/
public class PaperDaoImpl implements PaperDao{
    @Override
    public List<Paper> queryPaperByTitle(String title) throws SQLException {
        StringBuffer sql = new StringBuffer("select * from paperInfo where papertitle ");
    if(title != null && title!= "")
    {
        sql.append("like '%" + title + "%' ");
    }
        System.out.println(sql.toString());
        List<Paper> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                Paper tempPaper = new Paper();
                tempPaper.setPaperTitle(rs.getString("paperTitle"));
                tempPaper.setPaperAbstract(rs.getString("paperAbstract"));
                tempPaper.setPaperLink(rs.getString("paperLink"));
                tempPaper.setPaperKeyword(rs.getString("paperKeyword"));
                tempPaper.setPaperTypeYear(rs.getString("PaperTypeYear"));
                tempPaper.setPaperReleasetime(rs.getString("PaperReleasetime"));
                list.add(tempPaper);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally {
            rs.close();
            conn.close();
            stmt.close();
        }

        return list;
    }

    @Override
    public List<Paper> queryAll() throws SQLException {
        StringBuffer sql = new StringBuffer("select * from paperInfo");
        sql.append(" limit 0,4");
        System.out.println(sql.toString());
        List<Paper> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                Paper tempPaper = new Paper();
                tempPaper.setPaperTitle(rs.getString("paperTitle"));
                tempPaper.setPaperAbstract(rs.getString("paperAbstract"));
                tempPaper.setPaperLink(rs.getString("paperLink"));
                tempPaper.setPaperKeyword(rs.getString("paperKeyword"));
                tempPaper.setPaperTypeYear(rs.getString("PaperTypeYear"));
                tempPaper.setPaperReleasetime(rs.getString("PaperReleasetime"));
                list.add(tempPaper);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally {
            rs.close();
            conn.close();
            stmt.close();
        }
        return list;
    }

    @Override
    public int queryNumber(Paper p) throws SQLException {
        StringBuffer sql = new StringBuffer("select count(*) as paperCount from paperInfo p ");
        sql.append(" where 1 = 1");
        if (p.getPaperAbstract() != null && !"".equals(p.getPaperAbstract())) {
            sql.append(" and p.PaperAbstract like '%" + p.getPaperAbstract() + "%' ");
        }
        if (p.getPaperKeyword() != null && !"".equals(p.getPaperKeyword())) {
            sql.append(" and p.PaperKeyword like '%" + p.getPaperKeyword() + "%' ");
        }
        if (p.getPaperLink() != null && !"".equals(p.getPaperLink())) {
            sql.append(" and p.PaperLink like '%" + p.getPaperLink() + "%' ");
        }
        if (p.getPaperReleasetime() != null && !"".equals(p.getPaperReleasetime())) {
            sql.append(" and p.PaperReleasetime like '%" + p.getPaperReleasetime() + "%' ");
        }
        if (p.getPaperTitle() != null && !"".equals(p.getPaperTitle())) {
            sql.append(" and p.PaperTitle like '%" + p.getPaperTitle() + "%' ");
        }
        if (p.getPaperTypeYear() != null && !"".equals(p.getPaperTypeYear())) {
            sql.append(" and p.PaperTypeYear like '%" + p.getPaperTypeYear() + "%' ");
        }
        System.out.println(sql.toString());
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        int paperCount = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                paperCount = rs.getInt("paperCount");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return paperCount;
    }

    @Override
    public int deletePaper(String Link) {
        if(Link==null||Link=="")
            return 1;
        StringBuffer sql = new StringBuffer("delete from paperInfo  ");
        sql.append(" where paperLink= '"+Link+"'");
        System.out.println(sql.toString());
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        int result=999;
        try {
            stmt = conn.createStatement();
            result=stmt.executeUpdate(sql.toString());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Paper> queryUsers(Paper p, int pageNum, int lineNum) throws SQLException {
        int limit_x = (pageNum - 1) * lineNum;
        int limit_y = lineNum;
        StringBuffer sql = new StringBuffer("select * from paperInfo  ");
        sql.append(" where 1 = 1 ");
        if (p.getPaperAbstract() != null && !"".equals(p.getPaperAbstract())) {
            sql.append(" and PaperAbstract like '%" + p.getPaperAbstract() + "%' ");
        }
        if (p.getPaperKeyword() != null && !"".equals(p.getPaperKeyword())) {
            sql.append(" and PaperKeyword like '%" + p.getPaperKeyword() + "%' ");
        }
        if (p.getPaperLink() != null && !"".equals(p.getPaperLink())) {
            sql.append(" and PaperLink like '%" + p.getPaperLink() + "%' ");
        }
        if (p.getPaperReleasetime() != null && !"".equals(p.getPaperReleasetime())) {
            sql.append(" and PaperReleasetime like '%" + p.getPaperReleasetime() + "%' ");
        }
        if (p.getPaperTitle() != null && !"".equals(p.getPaperTitle())) {
            sql.append(" and PaperTitle like '%" + p.getPaperTitle() + "%' ");
        }
        if (p.getPaperTypeYear() != null && !"".equals(p.getPaperTypeYear())) {
            sql.append(" and PaperTypeYear like '%" + p.getPaperTypeYear() + "%' ");
        }
        sql.append(" order by PaperTitle ");
        sql.append(" limit " + limit_x + "," + limit_y);
        System.out.println(sql.toString());
        List<Paper> list = new ArrayList<>();
        MysqlDB mysqlDB = new MysqlDB();
        Connection conn = mysqlDB.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                Paper tempPaper = new Paper();
                tempPaper.setPaperTitle(rs.getString("paperTitle"));
                tempPaper.setPaperAbstract(rs.getString("paperAbstract"));
                tempPaper.setPaperLink(rs.getString("paperLink"));
                tempPaper.setPaperKeyword(rs.getString("paperKeyword"));
                tempPaper.setPaperTypeYear(rs.getString("PaperTypeYear"));
                tempPaper.setPaperReleasetime(rs.getString("PaperReleasetime"));
                list.add(tempPaper);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        PaperDao paperDao=new PaperDaoImpl();
        System.out.println(paperDao.queryAll());
    }
}
