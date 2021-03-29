package edu.fzu.paperAnalysis.service;

import edu.fzu.paperAnalysis.dao.PaperDao;
import edu.fzu.paperAnalysis.dao.PaperDaoImpl;
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
        PaperDao paperDao=new PaperDaoImpl();
        return paperDao.queryPaperByTitle(title);
    }

    @Override
    public List<Paper> queryAll() throws SQLException {
        PaperDao paperDao=new PaperDaoImpl();
        return paperDao.queryAll();
    }

    @Override
    public List<Paper> queryPapers(Paper paper, int pageNum, int lineNum) throws SQLException {
        PaperDao paperDao=new PaperDaoImpl();
        return paperDao.queryUsers(paper, pageNum, lineNum);
    }

    @Override
    public int queryNumber(Paper paper) throws SQLException {
        PaperDao paperDao=new PaperDaoImpl();
        return  paperDao.queryNumber(paper);


    }

    @Override
    public int deletePaper(String Link) {
        PaperDao paperDao=new PaperDaoImpl();
        return  paperDao.deletePaper(Link);
    }
}
