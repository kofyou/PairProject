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
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "4200");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");

        JSONObject requestJson= JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));
        paperserviceimpl.DeleteMyCollect(requestJson.getString("account"),requestJson.getString("title"));
        response.getWriter().print(true);
    }
}