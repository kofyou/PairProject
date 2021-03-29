package web;

import net.sf.json.JSONObject;
import pojo.Paper;
import service.impl.Paperserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyCollectServlet", value = "/MyCollectServlet")
public class MyCollectServlet extends HttpServlet
{
    Paperserviceimpl paperserviceimpl=new Paperserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username=(String)request.getAttribute("username");
        List<JSONObject> jsonObjects=new ArrayList<>();
        List<Paper> papers=paperserviceimpl.GetMyCollect(username);
        for(Paper paper:papers)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("title",paper.getTitle());
            jsonObject.put("author",paper.getAuthors());
            jsonObject.put("keyword",paper.getKeywords());
            jsonObject.put("info",paper.getTheabstract());
        }
        response.getWriter().print(jsonObjects);
    }
}
