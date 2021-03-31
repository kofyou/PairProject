package servlet;

import dao.PostDAO;
import dao.PostDAOImpl;
import pojo.Post;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    PostDAO userDAO = new PostDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        List<Post> userList = userDAO.list();
        String title = req.getParameter("title");
        String key = req.getParameter("key");
        for(Post post : userList){
            if (post.getTitle().equals(title)){
                req.setAttribute("title",title);
                req.setAttribute("content",post.getContent());
                req.setAttribute("link",post.getLink());
                req.setAttribute("keyWord",post.getKeyWord());
                req.setAttribute("time",post.getTime());
                req.setAttribute("platform",post.getPlatform());
                req.setAttribute("title",title);
                req.setAttribute("key",key);
                req.getRequestDispatcher("/articleDetails.jsp").forward(req,resp);
                break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        doPost(req,resp);
    }

}
