package edu.fzu.paperAnalysis.service;

import edu.fzu.paperAnalysis.vo.Paper;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName PaperServiceImpl
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/3/25 19:17
 * @Version 1.0
 **/
public class PaperServiceImpl implements PaperService{
    @Override
    public List<Paper> queryPaperByTitle(String title) throws SQLException {
        return null;
    }

    @Override
    public List<Paper> queryAll() throws SQLException {
        return null;
    }

    @Override
    public int queryNumber() throws SQLException {
        return 0;
    }
}
