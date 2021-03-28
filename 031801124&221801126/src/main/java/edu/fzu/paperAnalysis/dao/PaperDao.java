package edu.fzu.paperAnalysis.dao;

import edu.fzu.paperAnalysis.vo.Paper;

import java.sql.SQLException;
import java.util.List;

public interface PaperDao {
    List<Paper> queryPaperByTitle(String title) throws SQLException;
    List<Paper> queryAll() throws SQLException;
    int queryNumber(Paper p) throws SQLException;
    List<Paper> queryUsers(Paper paper, int pageNum, int lineNum) throws SQLException;


}
