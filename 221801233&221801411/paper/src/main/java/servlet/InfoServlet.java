package servlet;

import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InfoServlet", value = "/InfoServlet")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO=new UserDAOImpl();
        String search=req.getParameter("searchId");
        if(search!=null){
            int cur=Integer.parseInt(search);
            List<User> users = userDAO.list(cur,0);
            //System.out.println(users);
            int total = userDAO.getTotal();
            //page.setTotal(total);
            req.setAttribute("users", users);
            //request.setAttribute("page", page);
            RequestDispatcher rd;
            rd = req.getRequestDispatcher("/test.jsp?id=" + cur);
            rd.forward(req, resp);
        }
        else {
            int span=10;
            int cur = 1;
            String back = req.getParameter("id");
            if (back != null) {
                cur = Integer.parseInt(back);
            }
            List<User> users = userDAO.list(cur-cur%span+1,span);
            //System.out.println(users);
            int total = userDAO.getTotal();
            //page.setTotal(total);
            req.setAttribute("users", users);
            //request.setAttribute("page", page);
            RequestDispatcher rd;
            rd = req.getRequestDispatcher("/test.jsp?id=" + cur);
            rd.forward(req, resp);
        }
    }
}
