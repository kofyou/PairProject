package servlet;

import dao.KeywordDAO;
import dao.KeywordDAOImpl;
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
    KeywordDAO keywordDAO = new KeywordDAOImpl();

    /* 通过jsp传来的参数判断要执行的操作 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oper = "";
        if(req.getParameter("operation") != null)
            oper = req.getParameter("operation");
        switch (oper) {
            case "deletePaper":
                deletePaper(req, resp);
                break;
            case "showPaper":
                showPaper(req, resp);
                break;
            case "queryKeyPaper":
                queryKeyPaper(req, resp);
                break;
            default:
                queryPaper(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /* 模糊查询论文并分页 */
    public void queryPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("query");
        String pageNum = req.getParameter("pageNum");
        String changeNum = req.getParameter("changeNum");
        //linesPerPage:每页行数， page:当前页码， change:页码要改变的数量， totalNum:总论文数
        int linesPerPage = 20, page = 1, change = 0, totalNum = paperDAO.getTotal(str);
        //totalPage:总页数
        int totalPage = totalNum / linesPerPage + (totalNum % linesPerPage == 0 ? 0 : 1);

        if (totalPage == 0) {
            totalPage = 1;
        }
        if (pageNum != null && !"".equals(pageNum)) {
            page = Integer.parseInt(pageNum);
        }
        if (changeNum != null && !"".equals(changeNum)) {
            change = Integer.parseInt(changeNum);
        }
        if (!(page == 1 && change == -1) && !(page == totalPage && change == 1)) {
            page += change;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        ArrayList<Paper> paperList = paperDAO.list(str, page, linesPerPage);
        req.setAttribute("info", str);
        req.setAttribute("pageNum", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("totalNum", totalNum);
        req.setAttribute("paperList", paperList);
        req.getRequestDispatcher("/paperList.jsp").forward(req,resp);
    }

    /* 删除论文 */
    public void deletePaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("paperTitle");
        if (title != null && !"".equals(title) ) {
            paperDAO.delete(title);
        }
        String str = req.getParameter("str");
        String pageNum = req.getParameter("pn");
        String changeNum = req.getParameter("cn");
        System.out.println(str);
        System.out.println(pageNum);
        System.out.println(changeNum);
        //linesPerPage:每页行数， page:当前页码， change:页码要改变的数量， totalNum:总论文数
        int linesPerPage = 20, page = 1, change = 0, totalNum = paperDAO.getTotal(str);
        //totalPage:总页数
        int totalPage = totalNum / linesPerPage + (totalNum % linesPerPage == 0 ? 0 : 1);

        if (totalPage == 0) {
            totalPage = 1;
        }
        if (pageNum != null && !"".equals(pageNum)) {
            page = Integer.parseInt(pageNum);
        }
        if (changeNum != null && !"".equals(changeNum)) {
            change = Integer.parseInt(changeNum);
        }
        if (!(page == 1 && change == -1) && !(page == totalPage && change == 1)) {
            page += change;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        ArrayList<Paper> paperList = paperDAO.list(str, page, linesPerPage);
        req.setAttribute("info", str);
        req.setAttribute("pageNum", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("totalNum", totalNum);
        req.setAttribute("paperList", paperList);
        req.getRequestDispatcher("/paperList.jsp").forward(req,resp);
    }

    /* 查询单篇论文详细信息 */
    public void showPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("paperTitle");
        Paper paper = paperDAO.get(title);
        req.setAttribute("paperInfo", paper);
        req.getRequestDispatcher("/paper.jsp").forward(req,resp);
    }

    /* 通过关键词字段查询论文 */
    public void queryKeyPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("query");
        String pageNum = req.getParameter("pageNum");
        String changeNum = req.getParameter("changeNum");

        //linesPerPage:每页行数， page:当前页码， change:页码要改变的数量， totalNum:总论文数
        int linesPerPage = 20, page = 1, change = 0, totalNum = keywordDAO.getTotal(str);
        //totalPage:总页数
        int totalPage = totalNum / linesPerPage + (totalNum % linesPerPage == 0 ? 0 : 1);

        if (totalPage == 0) {
            totalPage = 1;
        }
        if (pageNum != null && !"".equals(pageNum)) {
            page = Integer.parseInt(pageNum);
        }
        if (changeNum != null && !"".equals(changeNum)) {
            change = Integer.parseInt(changeNum);
        }
        if (!(page == 1 && change == -1) && !(page == totalPage && change == 1)) {
            page += change;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        ArrayList<Paper> paperList = keywordDAO.keyList(str, page, linesPerPage);
        req.setAttribute("info", str);
        req.setAttribute("pageNum", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("totalNum", totalNum);
        req.setAttribute("paperList", paperList);
        req.getRequestDispatcher("/paperList.jsp").forward(req,resp);
    }
}
