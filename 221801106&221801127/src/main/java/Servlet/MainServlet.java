package Servlet;

import baseclass.Paper;
import baseclass.User;
import dao.UserDAO;
import dao.UserDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {
    public static UserDAO userDAO = new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        switch (request.getParameter("type")){

            case "crawl_paper":{
                try {
                    List<Paper> list = userDAO.findPaper(request.getParameter("search_all_paper").trim());
                    if (list.size() == 0)
                        list = null;
                    request.setAttribute("right1Search",list);
                    if (request.getAttribute("login_error")!=null){
                        System.out.println(request.getAttribute("login_error"+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
                    }
                    request.getRequestDispatcher("/right1.jsp").forward(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            } //case结束

            case "findmy_paper":{
                try {
                    String username = (String) request.getSession().getValue("username");
                    List<Paper> list = userDAO.findUserPaper(username);
                    if (list != null)
                    request.setAttribute("right2Search",list);
                    request.getRequestDispatcher("/right2.jsp").forward(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            } //case结束

            case "collect":{
                String username = (String) request.getSession().getValue("username");
                int papernumber = Integer.parseInt((String) request.getParameter("收藏"));
                Boolean b;
                try {
                    b = userDAO.UserInsertPaper(username,papernumber);
                    if (b == true)
                        request.setAttribute("collect_result","true");
                    else
                        request.setAttribute("collect_result","false");

                    List<String> usertenhotwords = userDAO.returnUserTenHotwords(username);
                    request.getSession().putValue("usertenhotwords",usertenhotwords);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.getRequestDispatcher("/right1.jsp").forward(request, response);
                break;
            } //case结束

            case "delete":{
                String username = (String) request.getSession().getValue("username");
                int papernumber = Integer.parseInt((String) request.getParameter("删除"));
                try {
                   userDAO.UserDeletePaper(username,papernumber);
                   request.setAttribute("delete_result","true");
                   List<String> usertenhotwords = userDAO.returnUserTenHotwords(username);
                   request.getSession().putValue("usertenhotwords",usertenhotwords);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.getRequestDispatcher("/right2.jsp").forward(request, response);
                break;
            } //case结束

            case  "changeMessage":{
                String name = (String) request.getSession().getValue("username");
                User user = (User) request.getSession().getValue("user");
                String password = user.getPassword();

                String nickname = (String) request.getParameter("nickname").trim();
                String school = (String) request.getParameter("school").trim();
                String discipline = (String) request.getParameter("discipline").trim();
                List<String> keys = new ArrayList<>();
                keys.add("nickname");
                keys.add("school");
                keys.add("profession");
                List<String> values = new ArrayList<>();
                values.add(nickname);
                values.add(school);
                values.add(discipline);

                try {
                    userDAO.updateUserInfo(keys,values,name);
                    User new_user = userDAO.findUser(name,password);
                    request.getSession().putValue("user",new_user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("changeMessage_result","true");
                request.getRequestDispatcher("/userhome.jsp").forward(request, response);
                break;
            }

            case  "changePassword":{
                String name = (String) request.getSession().getValue("username");
                String password = (String) request.getParameter("password").trim();
                List<String> keys = new ArrayList<>();
                keys.add("password");
                List<String> values = new ArrayList<>();
                values.add(password);

                try {
                    userDAO.updateUserInfo(keys,values,name);
                    User new_user = userDAO.findUser(name,password);
                    request.getSession().putValue("user",new_user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("changePassword_result","true");
                request.getRequestDispatcher("/userhome.jsp").forward(request, response);
                break;
            }
        }
    }


}
