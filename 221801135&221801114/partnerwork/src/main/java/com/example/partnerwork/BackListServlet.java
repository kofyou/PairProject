package com.example.partnerwork;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "BackListServlet", value = "/BackListServlet")
public class BackListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        int iterator = Integer.parseInt(request.getParameter("iterator"));
        int page = (int) Math.ceil((iterator+1)/8.0);//转换成页码
        request.setAttribute("currentPage",page);
        request.setAttribute("iterator", (page - 1) * 8);
        //数据库操作
        PaperDao paperDao = PaperDao.getInstance();
        int maxPage = (int) Math.ceil(paperDao.getTotal() / 8.0);
        request.setAttribute("totalNumber", paperDao.getTotal());
        request.setAttribute("maxPage", maxPage);
        List<Paper> list = paperDao.list(page, 8);
        request.setAttribute("list", list);
        request.getRequestDispatcher("listPage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}