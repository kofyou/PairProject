package web;

import dao.UserDaoimpl;
import pojo.User;
import service.impl.Userserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateUserInfoServlet", value = "/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet
{

    Userserviceimpl userserviceimpl=new Userserviceimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();

        user.setAccount((String)request.getAttribute("username"));
        user.setUsername((String)request.getAttribute("name"));
        user.setAddress((String)request.getAttribute("address"));
        user.setCompany((String)request.getAttribute("company"));
        user.setInfo((String)request.getAttribute("info"));

        userserviceimpl.UpdateInfo(user);

        response.getWriter().print(true);
    }
}
