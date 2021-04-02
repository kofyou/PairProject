package edu.fzu.postsearch.servlet;
import edu.fzu.postsearch.dao.PostDAOimpl;
import edu.fzu.postsearch.pojo.Post;
import edu.fzu.postsearch.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/postList")

public class PostServlet extends BaseBackServlet{
    private PostDAO postDAO;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = (List<Post>) req.getAttribute("posts");// 取得共享里面的数据
        if (posts == null){
            String search = req.getParameter("search");
            req.setAttribute("search",search);
            postDAO = new PostDAOimpl();
            if(search.equals(""))posts = postDAO.list();
            else posts = postDAO.listSearch(search);
        }
        // 接收分页页面传递过来的页面数
        String strNum = req.getParameter("pageNum");
        int pageNum = 0;// 表示当前要显示的页面数
        int maxPage = 0;// 最大页
        int pageCount = posts.size();// 得到查询出来的所有数据的数目
        // 如果是第一次执行,就会接收不到数据
        // 计算出要分多少页
        if (pageCount % 31 == 0) {
            maxPage = pageCount / 31;
        } else {
            maxPage = pageCount / 31 + 1;
        }
        if ((strNum == null)||(strNum.equals(""))) {
            strNum = "0";
        } else {// 接收到了用户点击的第几(pageNum)页
            pageNum = Integer.parseInt(strNum);
            if (pageNum < 0 || pageNum > maxPage)pageNum = 0;
        }

        req.setAttribute("maxPage", maxPage);// 存储最大页数
        req.setAttribute("pageNum", pageNum);// 将当前页面存储起来,给分页页面使用
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/postList.jsp").forward(req, resp);
    }

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
