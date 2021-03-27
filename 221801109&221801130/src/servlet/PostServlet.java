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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/hello")
public class PostServlet extends HttpServlet {

    PostDAO postDAO = new PostDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        List<Post> list = new ArrayList<>() ;
        int count = 0;
        String string = " ";
        if (req.getParameter("count") != null) {
            count = Integer.parseInt(req.getParameter("count"));
            string = req.getParameter("string");
        } else if (req.getParameter("nex") != null) {
            count = Integer.parseInt(req.getParameter("nex")) + 1;
            string = req.getParameter("string2");
        } else if (req.getParameter("pre") != null) {
            count = Integer.parseInt(req.getParameter("pre")) - 1;
            string = req.getParameter("string1");
        } else if (req.getParameter("string") != null){
            string = req.getParameter("string");
        }
        String sql = "select * from post";
        List<Post> postList = new ArrayList<>();
        postList = postDAO.list(string);

        int page = 0;
        if (postList.size() == 0){
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
        if (postList.size() % 1 == 0) {// 如果记录总条数对每页显示记录数取整等于0，则表示页面数刚好分完。
            page = postList.size() / 1;
        } else {// 如果取不尽，那么就添加一页来放剩余的记录
            page = postList.size() / 1 + 1;
        }
        if (count < 0){
            count = 0;
        } else if (count > postList.size()-1) {
            count = postList.size()-1;
        }
        list = postList.subList(count, count+1);

        req.setAttribute("key",string);
        req.setAttribute("postList",list);
        req.setAttribute("count",count);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

}
