package edu.fzu.paperAnalysis.dao;

import edu.fzu.paperAnalysis.vo.Paper;

import java.sql.SQLException;
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
