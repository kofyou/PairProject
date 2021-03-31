package com.example.partnerwork;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "deleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        int iterator =Integer.parseInt(request.getParameter("iterator"));
        int page =Integer.parseInt(request.getParameter("page"));
        //数据库操作
        PaperDao paperDao = PaperDao.getInstance();
        paperDao.delete(iterator);
        int maxPage = (int) Math.ceil(paperDao.getTotal() / 8.0);
        request.setAttribute("maxPage", maxPage);
        if (page > maxPage){
            page = maxPage;
        }
        request.setAttribute("totalNumber", paperDao.getTotal());
        request.setAttribute("currentPage",page);
        request.setAttribute("iterator", (page - 1) * 8);
        List<Paper> list = paperDao.list(page, 8);
        request.setAttribute("list", list);
        request.getRequestDispatcher("listPage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}