package web;

import service.impl.Userserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Userserviceimpl userserviceimpl=new Userserviceimpl();
        String[] accountinfo={request.getParameter("userName"),request.getParameter("password")};
        response.getWriter().print(userserviceimpl.Register(accountinfo));
    }
}
