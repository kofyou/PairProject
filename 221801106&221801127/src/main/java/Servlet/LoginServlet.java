package Servlet;

import baseclass.User;
import dao.UserDAO;
import dao.UserDAOImpl;
import util.BaseUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet{
    public static User user;
    public static UserDAO userDAO = new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("type").equals("login")){
            String account =(String) request.getParameter("account").trim();
            String password =(String) request.getParameter("password").trim();
            try {
                User user = userDAO.findUser(account,password);
                if (user != null){
                    user = userDAO.findUser(account,password);
                    List<Map.Entry<String,Integer>> list = userDAO.getTenHotwords("ALL");
                    HttpSession session = request.getSession();
                    request.setAttribute("tensearchwords",list);

                    session.putValue("username",user.getName());
                    session.putValue("tensearchwords",list);
                    session.putValue("user",user);
                    String ss = userDAO.getTenHotwords("CVPR").get(0).getKey();
                    session.putValue("CVPR",userDAO.getTenHotwords("CVPR"));
                    session.putValue("ECCV",userDAO.getTenHotwords("ECCV"));
                    session.putValue("ICCV",userDAO.getTenHotwords("ICCV"));
                    session.putValue("usertenhotwords",userDAO.returnUserTenHotwords(user.getName()));


                    request.getRequestDispatcher("/right1.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("login_error","true");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (request.getParameter("type").equals("register")){
            String account = request.getParameter("account").trim();
            String password = request.getParameter("password").trim();
            String password2 = request.getParameter("password2").trim();
            if (!password.equals(password2)){
                request.setAttribute("register_error","true1");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            try {
                boolean b = userDAO.InsertUser(account,password);
                if (b == false){
                    request.setAttribute("register_error","true2");
                    System.out.println("!@#$%^&*()+++++++++++++++++++++++++++++++");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            request.setAttribute("register_success","true");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }  //else if结束


    }

}
