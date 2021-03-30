package web;

import net.sf.json.JSONObject;
import service.impl.Userserviceimpl;
import utils.RequestToJson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet
{
    Userserviceimpl userserviceimpl=new Userserviceimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject requestJson= JSONObject.fromObject(
                RequestToJson.getRequestPostStr(request));

        String account=requestJson.getString("account");
        String password=requestJson.getString("password");
        if (userserviceimpl.IsUserExist(account))
        {
            response.getWriter().print(false);
        }
        else
        {
            String[] accountinfo={account,password};
            userserviceimpl.Register(accountinfo);
            response.getWriter().print(true);
        }
    }
}
