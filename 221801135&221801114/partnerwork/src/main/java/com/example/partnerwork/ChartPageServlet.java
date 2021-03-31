package com.example.partnerwork;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ChartPageServlet", value = "/ChartPageServlet")
public class ChartPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        //直接从数据库拿关键词最高的10个过来，然后放进request里面，然后去渲染图表页面（ChartPge.jsp）
        //数据库操作
        PaperDao paperDao = PaperDao.getInstance();
        paperDao.setSortTagList();
        List<HashMap.Entry<String, Integer>> list = paperDao.getSortTagList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("chartPage.jsp").forward(request,response);
    }

    public void destroy() {
    }
}