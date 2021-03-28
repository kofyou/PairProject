package servlet;

import dao.PaperDAO;
import dao.PaperDAOImpl;
import pojo.Paper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListServlet", value = "/ListServlet")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PaperDAO paperDAO = new PaperDAOImpl();
        String option = request.getParameter("option");
        String text = request.getParameter("search");
        System.out.println(option + " " + text);
        List<Paper> list = new ArrayList<>();

        if ("title".equals(option)) {
            System.out.println(option + " " + text);
            list = paperDAO.listGetByTitle(text);
            for (Paper paper : list) {
                System.out.println(paper.getTitle());
                System.out.println(paper.getSummary());
                System.out.println(paper.getLink());
                System.out.println(paper.getKeyword());
                System.out.println(paper.getYear());
                System.out.println(paper.getType());
            }
        }

        if ("keyword".equals(option)) {
            list = paperDAO.listGetByKeyword(text);
        }

        if ("type".equals(option)) {
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
