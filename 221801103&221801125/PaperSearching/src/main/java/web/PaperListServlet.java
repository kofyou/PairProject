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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PaperListServlet", value = "/PaperListServlet")
public class PaperListServlet extends HttpServlet
{
    Paperserviceimpl paperserviceimpl=new Paperserviceimpl();
    static List<JSONObject> showPapers=new ArrayList<>();
    JSONObject requestJson;
    String searchedWord="";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "4200");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");

        requestJson=JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));

        if(requestJson.getString("methods").equals("getPages"))
        {
            if(requestJson.getInt("type")==1)
            {
                showPapers.clear();

                int type=Integer.parseInt(requestJson.getString("type"));
                String account=requestJson.getString("account");

                List<Paper> papers=paperserviceimpl.GetPaperList("",type);

                for (int i=0;i<papers.size();i++)
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("title",papers.get(i).getTitle());
                    String[] authorList=papers.get(i).getAuthors().split("//");
                    jsonObject.put("author",authorList);
                    String[] keywordList=papers.get(i).getKeywords().split("//");
                    jsonObject.put("keyword",keywordList);
                    jsonObject.put("info",papers.get(i).getTheabstract());
                    jsonObject.put("link",papers.get(i).getPaperlink());
                    jsonObject.put("iscollect",paperserviceimpl.IsCollected(account,papers.get(i).getTitle()));
                    showPapers.add(jsonObject);
                }
            }
            String curSearchword=requestJson.getString("str");
            if (!searchedWord.equals(curSearchword))
            {
                searchedWord=curSearchword;
                showPapers.clear();

                int type=Integer.parseInt(requestJson.getString("type"));
                String account=requestJson.getString("account");

                List<Paper> papers=paperserviceimpl.GetPaperList(curSearchword,type);

                for (int i=0;i<papers.size();i++)
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("title",papers.get(i).getTitle());
                    String[] authorList=papers.get(i).getAuthors().split("//");
                    jsonObject.put("author",authorList);
                    String[] keywordList=papers.get(i).getKeywords().split("//");
                    jsonObject.put("keyword",keywordList);
                    jsonObject.put("info",papers.get(i).getTheabstract());
                    jsonObject.put("link",papers.get(i).getPaperlink());
                    jsonObject.put("iscollect",paperserviceimpl.IsCollected(account,papers.get(i).getTitle()));
                    showPapers.add(jsonObject);
                }
                System.out.println(showPapers.size());
            }
            response.getWriter().print(showPapers.size());
        }
        else if(requestJson.getString("methods").equals("getSearchList"))
        {
            int index=requestJson.getInt("page");
            if(index*8<=showPapers.size())
            {
                response.getWriter().print(showPapers.subList((index - 1) * 8, index * 8));
            }
            else
            {
                response.getWriter().print(showPapers.subList((index - 1) * 8, (index - 1) * 8+showPapers.size()%8));
            }
        }
    }

}
