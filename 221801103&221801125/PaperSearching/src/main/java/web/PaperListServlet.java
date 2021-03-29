package web;

import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.Paper;
import service.impl.Paperserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
        String[] searchingString = (String[])JSONArray.fromObject
                (request.getAttribute("str")).toArray();
        int type=(int)request.getAttribute("type");

        List<Paper> papers=paperserviceimpl.GetPaperList(searchingString,type);


    }
}
