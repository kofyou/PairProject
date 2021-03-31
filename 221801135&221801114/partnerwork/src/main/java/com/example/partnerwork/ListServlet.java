package com.example.partnerwork;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "listServlet", value = "/ListServlet")
public class ListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        PaperDao paperDao = PaperDao.getInstance();
        List<Paper> list = paperDao.list(1, 8);
        int maxPage = (int) Math.ceil(paperDao.getTotal() / 8.0);
        request.setAttribute("totalNumber", paperDao.getTotal());
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("currentPage", 1);
        request.setAttribute("list", list);
        request.setAttribute("iterator", 0);
        request.getRequestDispatcher("listPage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}