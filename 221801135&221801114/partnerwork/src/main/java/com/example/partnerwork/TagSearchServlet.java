package com.example.partnerwork;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "tagSearchServlet", value = "/TagSearchServlet")
public class TagSearchServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        String search = (String) request.getParameter("search");
        request.setAttribute("search",search);
        System.out.println(search);
        //数据库操作
        PaperDao paperDao = PaperDao.getInstance();
        List<Paper> list = paperDao.findByTag(search);
        request.setAttribute("iterator", 0);
        request.setAttribute("list", list);
        request.setAttribute("currentPage", 1);
        int maxPage;
        maxPage = (int) Math.ceil(paperDao.getTotal() / 8.0);
        request.setAttribute("totalNumber", paperDao.getTotal());
        request.setAttribute("maxPage", maxPage);
        request.getRequestDispatcher("listPage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}