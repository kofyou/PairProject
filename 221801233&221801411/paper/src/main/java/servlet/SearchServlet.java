package servlet;

import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO=new UserDAOImpl();
        String returnid=req.getParameter("id");
        int id=Integer.parseInt(returnid);
        User user=userDAO.get(id);
        req.setAttribute("user", user);
        //request.setAttribute("page", page);
        RequestDispatcher rd;
        rd = req.getRequestDispatcher("/admin/paperInfo.jsp?id=" +id);
        rd.forward(req, resp);
    }



}
