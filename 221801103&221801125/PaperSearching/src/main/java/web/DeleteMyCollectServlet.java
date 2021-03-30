package web;

import net.sf.json.JSONObject;
import service.impl.Paperserviceimpl;
import utils.RequestToJson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteMyCollectServlet", value = "/DeleteMyCollectServlet")
public class DeleteMyCollectServlet extends HttpServlet
{
    Paperserviceimpl paperserviceimpl=new Paperserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestJson= JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));
        paperserviceimpl.DeleteMyCollect(requestJson.getString("account"),requestJson.getString("title"));
        response.getWriter().print(true);
    }
}
