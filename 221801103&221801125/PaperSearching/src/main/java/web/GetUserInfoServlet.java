package web;

import net.sf.json.JSONObject;
import pojo.User;
import service.impl.Userserviceimpl;
import utils.RequestToJson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetUserInfoServlet", value = "/GetUserInfoServlet")
public class GetUserInfoServlet extends HttpServlet {
    Userserviceimpl userserviceimpl=new Userserviceimpl();
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

        User user= userserviceimpl.GetUserInfo(requestJson.getString("account"));


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("username",user.getUsername());
        jsonObject.put("company",user.getCompany());
        jsonObject.put("address",user.getAddress());
        jsonObject.put("info",user.getInfo());

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }
}
