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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        List<Post> list = new ArrayList<>() ;
        String string = req.getParameter("string");
        String sql = "select * from post";
        List<Post> postList = new ArrayList<>();
       int i = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                if (title.equals(string)){
                    String content = rs.getString("content");
                    String link = rs.getString("link");
                    String keyWord = rs.getString("keyWord");
                    String time = rs.getString("time");
                    String platform = rs.getString("platform");
                    postList.add(new Post(title, content, link, keyWord, time, platform));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("key",string);
        req.setAttribute("postList",postList);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
