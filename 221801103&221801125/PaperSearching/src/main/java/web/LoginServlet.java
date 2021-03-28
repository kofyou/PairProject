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


@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        JSONObject jsonObject=new JSONObject();
        Userserviceimpl userserviceimpl=new Userserviceimpl();
        UserDaoimpl userDaoimpl=new UserDaoimpl();
        if(!userDaoimpl.IsUserExist(userName))
        {
            jsonObject.put("type","0");
            jsonObject.put("name","");
            jsonObject.put("address","");
            jsonObject.put("company","");
            jsonObject.put("info","");
            response.getWriter().print(jsonObject);
        }

        User user=userDaoimpl.GetUser(userName);
        if(user.getPassword().equals(password))
        {
            jsonObject.put("type","1");
            jsonObject.put("name",user.getUsername());
            jsonObject.put("address",user.getAddress());
            jsonObject.put("company",user.getCompany());
            jsonObject.put("info",user.getInfo());
            response.getWriter().print(jsonObject);
        }
        else
        {
            jsonObject.put("type","0");
            jsonObject.put("name","");
            jsonObject.put("address","");
            jsonObject.put("company","");
            jsonObject.put("info","");
            response.getWriter().print(jsonObject);
        }
    }
}
