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
        System.out.println(req.getParameter("key"));
        if (req.getParameter("count") != null) { //首页
            count = Integer.parseInt(req.getParameter("count"));
            string = req.getParameter("string");
        } else if (req.getParameter("nex") != null) { //往后翻页
            count = (Integer.parseInt(req.getParameter("nex")) + 1) * 10;
            string = req.getParameter("string2");
        } else if (req.getParameter("pre") != null) { //往前翻页
            count = (Integer.parseInt(req.getParameter("pre")) - 1) *10;
            string = req.getParameter("string1");
        } else if (req.getParameter("string") != null) { //返回事件后跳转原来搜索内容
            string = req.getParameter("string");
        } else if (this.getServletContext().getAttribute("key") != null){ //删除文章后跳转回原来搜索内容
            string = (String) this.getServletContext().getAttribute("key");
        }
        List<Post> postList = new ArrayList<>();
        postList = postDAO.list(string);
        int size = postList.size();
        int page = 1;
        if (size == 0){
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
        if (count < 0){
            count = 0;
        } else if (count > size) {
            count = count - 10;
        }
        if (size % 10 == 0) {// 如果记录总条数对每页显示记录数取整等于0，则表示页面数刚好分完。
            page = size / 10;
            list = postList.subList(count, count+10);
        } else {// 如果取不尽，那么就添加一页来放剩余的记录
            if (size <= 10) {
                page = 1;
                list = postList.subList(count, size);
            } else {
                page = postList.size() / 10 + 1;
                if (count < (page-1)*10 ) {
                    list = postList.subList(count, count+10);
                } else {
                    list = postList.subList(count, count+postList.size()-(page-1)*10);
                }
            }
        }
        count /= 10;
        req.setAttribute("key",string);
        req.setAttribute("postList",list);
        req.setAttribute("count",count);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        doPost(req,resp);
    }

}
