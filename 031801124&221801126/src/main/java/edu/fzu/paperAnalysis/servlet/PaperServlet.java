package edu.fzu.paperAnalysis.servlet;

import edu.fzu.paperAnalysis.dao.PaperDao;
import edu.fzu.paperAnalysis.dao.PaperDaoImpl;
import edu.fzu.paperAnalysis.service.PaperServiceImpl;
import edu.fzu.paperAnalysis.vo.Paper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName PaperServlet
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/3/25 19:20
 * @Version 1.0
 **/
public class PaperServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaperDao paperDao=new PaperDaoImpl();
        List<Paper> list= null;
        try {
            list = paperDao.queryAll();
            for(Paper temppaper : list)
            {
                System.out.println(temppaper.getPaperAbstract());

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("paperList2", list);
        req.getRequestDispatcher("/paperList.jsp").forward(req, resp);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaperDao paperDao=new PaperDaoImpl();
        List<Paper> list= null;
        try {
            list = paperDao.queryAll();
            for(Paper temppaper : list)
            {
                System.out.println(temppaper.getPaperAbstract());

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("paperList2", list);
        req.getRequestDispatcher("/paperList.jsp").forward(req, resp);

    }
}
