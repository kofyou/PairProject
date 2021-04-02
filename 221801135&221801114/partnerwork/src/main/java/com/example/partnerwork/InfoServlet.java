package com.example.partnerwork;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "infoServlet", value = "/InfoServlet")
public class InfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        int iterator =Integer.parseInt(request.getParameter("iterator"));
        request.setAttribute("iterator",iterator);

        PaperDao paperDao = PaperDao.getInstance();
        Paper paper = paperDao.findPaperByIterator(iterator);
        request.setAttribute("paper", paper);
        request.getRequestDispatcher("paperInfo.jsp").forward(request,response);
    }

    public void destroy() {
    }
}