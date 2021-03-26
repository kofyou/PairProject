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

@WebServlet(name = "PaperQueryServlet", value = "/PaperQueryServlet")
public class PaperQueryServlet extends HttpServlet {

    PaperDAO paperDAO = new PaperDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        queryPaper(req, resp);
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

}
