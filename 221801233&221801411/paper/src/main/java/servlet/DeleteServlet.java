package servlet;

import dao.UserDAO;
import dao.UserDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String returnint=req.getParameter("id");
        int id=0;
        if(returnint!=null){
            id=Integer.parseInt(returnint);
        }
        UserDAO userDAO=new UserDAOImpl();
        if(id!=0)
        userDAO.delete(id);
        int returnid=id-(id%10)+1;
        RequestDispatcher rd;
        rd=req.getRequestDispatcher("InfoServlet?id="+returnid);
        rd.forward(req, resp);
    }
}
