package cn.itcast.servlet;

import cn.itcast.dao.KeynumDaoImpl;
import cn.itcast.dao.PaperInfoDAO;
import cn.itcast.dao.PaperInfoDAOImpl;
import cn.itcast.domain.Keynum;
import cn.itcast.domain.PaperInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/homepage")
public class UserServlet extends HttpServlet{
    PaperInfoDAO dao;
    KeynumDaoImpl keynumDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao = new PaperInfoDAOImpl();
        List<PaperInfo> paperInfos = dao.selectPaperInfos();
        if(req.getParameter("search")!=null)
        {
            paperInfos = dao.getinfo(req.getParameter("search"));
            req.setAttribute("paperInfos",paperInfos);
            req.getRequestDispatcher("/front/index.jsp").forward(req,resp);
        }
        else if(req.getParameter("delete")!=null)
        {
            dao.delete(req.getParameter("delete"));
            List<PaperInfo> paperInfos1 = dao.selectPaperInfos();
            req.setAttribute("paperInfos",paperInfos1);
            req.getRequestDispatcher("/front/index.jsp").forward(req,resp);
        }
        else if(req.getParameter("p")!=null)
        {
            paperInfos = dao.getkey(req.getParameter("p"));
            req.setAttribute("paperInfos",paperInfos);
            req.getRequestDispatcher("/front/index.jsp").forward(req,resp);
        }
        else if(req.getParameter("view")!=null)
        {
            System.out.println(dao.selectPaperInfo(req.getParameter("view")));
            System.out.println(req.getParameter("view"));
            req.setAttribute("paperInfo",dao.selectPaperInfo(req.getParameter("view")));
            req.getRequestDispatcher("/front/user.jsp").forward(req,resp);
        }
        else
        {
            req.setAttribute("paperInfos",paperInfos);
            req.getRequestDispatcher("/front/index.jsp").forward(req,resp);
        }
}
}
