package web;

import com.mysql.cj.xdevapi.JsonArray;
import dao.UserDaoimpl;
import net.sf.json.JSONObject;
import service.impl.Userserviceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import pojo.*;
import utils.Jdbcutils;
import utils.RequestToJson;


@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet
{

    UserDaoimpl userDaoimpl=new UserDaoimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        JSONObject requestJson= JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));

        String account=requestJson.getString("account");
        String password=requestJson.getString("password");

        JSONObject jsonObject=new JSONObject();

        if(!userDaoimpl.IsUserExist(account))
        {
            jsonObject.put("type",false);
            jsonObject.put("name","");
            jsonObject.put("address","");
            jsonObject.put("company","");
            jsonObject.put("info","");
            response.getWriter().print(jsonObject);
        }

        User user=userDaoimpl.GetUser(account);
        if(user.getPassword().equals(password))
        {
            jsonObject.put("type",true);
            jsonObject.put("name",user.getUsername());
            jsonObject.put("address",user.getAddress());
            jsonObject.put("company",user.getCompany());
            jsonObject.put("info",user.getInfo());
            response.getWriter().print(jsonObject);
        }
        else
        {
            jsonObject.put("type",false);
            jsonObject.put("name","");
            jsonObject.put("address","");
            jsonObject.put("company","");
            jsonObject.put("info","");
            response.getWriter().print(jsonObject);
        }
    }
}
