package web;

import net.sf.json.JSONObject;
import pojo.Paper;
import service.impl.Paperserviceimpl;
import utils.RequestToJson;

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
    static List<Paper> papers=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JSONObject requestJson=JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));
        String account=requestJson.getString("account");
        List<JSONObject> jsonObjects=new ArrayList<>();
        List<Paper> papers=paperserviceimpl.GetMyCollect(account);
        for(Paper paper:papers)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("title",paper.getTitle());
            String[] authorList=paper.getAuthors().split("//");
            jsonObject.put("author",authorList);
            String[] keywordList=paper.getKeywords().split("//");
            jsonObject.put("keyword",keywordList);
            jsonObject.put("abstract",paper.getTheabstract());
            jsonObject.put("link",paper.getPaperlink());
            jsonObjects.add(jsonObject);
        }
        response.getWriter().print(jsonObjects);
    }
}
