package servlet;

import dao.PostDAO;
import dao.PostDAOImpl;
import pojo.Post;
import util.DBUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    PostDAO userDAO = new PostDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("string1");

        userDAO.delete(title);
        req.setAttribute("string",title);
        req.getRequestDispatcher("/hello").forward(req,resp);
    }



}

