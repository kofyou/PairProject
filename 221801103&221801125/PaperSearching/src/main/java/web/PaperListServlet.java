package web;

import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
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

@WebServlet(name = "PaperListServlet", value = "/PaperListServlet")
public class PaperListServlet extends HttpServlet
{
    Paperserviceimpl paperserviceimpl=new Paperserviceimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestJson=JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));
        Object[] searchingString = JSONArray.fromObject
                (requestJson.getString("str")).toArray();
        ArrayList<String> arrayList=new ArrayList<>();
        for(Object o:searchingString)
        {
            arrayList.add(o.toString());
        }

        int type=Integer.parseInt(requestJson.getString("type"));
        String account=requestJson.getString("account");

        List<JSONObject> jsonObjects=new ArrayList<>();
        List<Paper> papers=paperserviceimpl.GetPaperList(arrayList,type);
        for(Paper paper:papers)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("title",paper.getTitle());
            String[] authorList=paper.getAuthors().split("//");
            jsonObject.put("author",authorList);
            String[] keywordList=paper.getKeywords().split("//");
            jsonObject.put("keyword",keywordList);
            jsonObject.put("info",paper.getTheabstract());
            jsonObject.put("iscollect",paperserviceimpl.IsCollected(account,paper.getTitle()));
            jsonObjects.add(jsonObject);
        }
        response.getWriter().print(jsonObjects);
    }
}
