package com.example.partnerwork;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "nextpageServlet", value = "/NextPageServlet")
public class NextPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        int page = Integer.parseInt(request.getParameter("page"));
        //数据库操作
        PaperDao paperDao = PaperDao.getInstance();
        int maxPage = (int) Math.ceil(paperDao.getTotal() / 8.0);
        if (page + 1 > maxPage){
            page = maxPage;
        }
        int iterator = (page - 1) * 8;
        request.setAttribute("totalNumber", paperDao.getTotal());
        request.setAttribute("iterator", iterator);
        request.setAttribute("currentPage",page);
        request.setAttribute("maxPage", maxPage);
        List<Paper> list = paperDao.list(page, 8);
        request.setAttribute("list", list);
        request.getRequestDispatcher("listPage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}