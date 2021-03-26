package servlet;

import dao.PaperDAO;
import dao.PaperDAOImpl;
import pojo.Paper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PaperListServlet", value = "/PaperListServlet")
public class PaperListServlet extends HttpServlet {

    PaperDAO paperDAO = new PaperDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if (type == null )
            type = "1";
        switch (type) {
            case "2":
                deletePaper(req, resp);
                break;
            case "3":
                showPaper(req, resp);
                break;
            case "1":
                queryPaper(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void queryPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("query");
        ArrayList<Paper> paperList = paperDAO.list(str);
        req.setAttribute("paperList",paperList);
        req.getRequestDispatcher("/paperList.jsp").forward(req,resp);
    }

    public void deletePaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("paperTitle");
        req.getRequestDispatcher("/paper.jsp").forward(req,resp);
    }

    public void showPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("paperTitle");
        Paper paper = paperDAO.get(title);
        req.setAttribute("paperInfo", paper);
        req.getRequestDispatcher("/paper.jsp").forward(req,resp);
    }
}
