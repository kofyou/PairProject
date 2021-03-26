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
        String oper = "";
        if(req.getParameter("operation") != null)
            oper = req.getParameter("operation");
        if (oper.equals("deletePaper")) {
            deletePaper(req, resp);
        }
        else if(oper.equals("showPaper")) {
            showPaper(req, resp);
        }
        else{
            queryPaper(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void queryPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("query");
        Cookie c1 = new Cookie("queryInfo", str);
        resp.addCookie(c1);
        ArrayList<Paper> paperList = paperDAO.list(str);
        req.setAttribute("paperList", paperList);
        req.getRequestDispatcher("/paperList.jsp").forward(req,resp);
    }

    public void deletePaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("paperTitle");
        paperDAO.delete(title);
        Cookie[] cookies = req.getCookies();
        String str = null;
        if(cookies != null) {
            for(Cookie cookie:cookies) {
                if(cookie.getName().equals("queryInfo")) {
                    str = cookie.getValue();
                    break;
                }
            }
        }
        ArrayList<Paper> paperList = paperDAO.list(str);
        req.setAttribute("paperList", paperList);
        req.getRequestDispatcher("/paperList.jsp").forward(req,resp);
    }

    public void showPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("paperTitle");
        Paper paper = paperDAO.get(title);
        req.setAttribute("paperInfo", paper);
        req.getRequestDispatcher("/paper.jsp").forward(req,resp);
    }
}
