package edu.fzu.paperAnalysis.service;

import edu.fzu.paperAnalysis.vo.Paper;

import java.sql.SQLException;
import java.util.List;

public interface PaperService {
    List<Paper> queryPaperByTitle(String title) throws SQLException;
    List<Paper> queryAll() throws SQLException;
    int queryNumber(Paper paper) throws SQLException;
    List<Paper> queryPapers(Paper paper, int pageNum, int lineNum) throws SQLException;
}
