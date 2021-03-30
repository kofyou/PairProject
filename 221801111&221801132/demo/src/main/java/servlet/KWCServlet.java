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

@WebServlet("/KWCServlet")
public class KWCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("button");
        PaperDAO paperDAO = new PaperDAOImpl();
        List<Paper> list = new ArrayList<>();
        list = paperDAO.listGetByKeyword(keyword);

        request.setAttribute("list", list);
        request.getRequestDispatcher("KWCList.jsp").forward(request, response);
    }
}
