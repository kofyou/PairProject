package cn.itcast.servlet;

import cn.itcast.dao.UserDAO;
import cn.itcast.dao.UserDAOImpl;
import cn.itcast.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet{
    UserDAO dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao = new UserDAOImpl();
        List<User> users = dao.selectUsers();
        req.setAttribute("users",users);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
}
}
