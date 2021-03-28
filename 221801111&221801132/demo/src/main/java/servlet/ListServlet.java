package servlet;

import dao.PaperDAO;
import dao.PaperDAOImpl;
import pojo.Paper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaperDAO paperDAO = new PaperDAOImpl();
        String option = request.getParameter("option");
        String text = request.getParameter("search");
        List<Paper> list = new ArrayList<>();
        switch (option) {
            case "title":
                list = paperDAO.listGetByTitle(text);
            case "keyword":
                list = paperDAO.listGetByKeyword(text);
            case "type":
                list = paperDAO.listGetByType(text);
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("PaperList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
