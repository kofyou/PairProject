package web;

import service.impl.Paperserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteMyCollectServlet", value = "/DeleteMyCollectServlet")
public class DeleteMyCollectServlet extends HttpServlet
{
    Paperserviceimpl paperserviceimpl=new Paperserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account=(String)request.getAttribute("account");
        String title=(String)request.getAttribute("title");
        paperserviceimpl.DeleteMyCollect(account,title);
    }
}
