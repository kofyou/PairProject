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
                tempPaper.setPaperAuthor(rs.getString("paperAuthor"));
                tempPaper.setPaperKeywords("paperKeywords");
                tempPaper.setPaperYear("paperYear");
                tempPaper.setPaperPublication("paperPublication");
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
                tempPaper.setPaperAuthor(rs.getString("paperAuthor"));
                tempPaper.setPaperKeywords("paperKeywords");
                tempPaper.setPaperYear("paperYear");
                tempPaper.setPaperPublication("paperPublication");
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
    public int queryNumber() throws SQLException {
        return 0;
    }

    public static void main(String[] args) throws SQLException {
        PaperDao paperDao=new PaperDaoImpl();
        System.out.println(paperDao.queryAll());
    }
}
