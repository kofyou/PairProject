package web;

import com.mysql.cj.xdevapi.JsonArray;
import dao.PaperDaoimpl;
import net.sf.json.JSONObject;
import pojo.Paper;
import service.impl.Paperserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaperDetailsServlet", value = "/PaperDetailsServlet")
public class PaperDetailsServlet extends HttpServlet
{
    Paperserviceimpl paperserviceimpl=new Paperserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=(String)request.getAttribute("title");

        Paper paper=paperserviceimpl.GetAPaper(title);

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("title",paper.getTitle());
        String[] authorList=paper.getAuthors().split("\\\\");
        jsonObject.put("author",authorList);
        String[] keywordList=paper.getAuthors().split("\\\\");
        jsonObject.put("keyword",keywordList);
        jsonObject.put("abstract",paper.getTheabstract());
        jsonObject.put("link",paper.getPaperlink());

        response.getWriter().print(jsonObject);
    }
}
