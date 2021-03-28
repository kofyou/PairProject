package service.impl;

import dao.PaperDaoimpl;
import net.sf.json.JSONObject;
import pojo.Paper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaperDetailsServlet", value = "/PaperDetailsServlet")
public class PaperDetailsServlet extends HttpServlet
{

    PaperDaoimpl paperDaoimpl=new PaperDaoimpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=(String)request.getAttribute("title");

        Paper paper=paperDaoimpl.GetAPaper(title);

        JSONObject jsonObject=new JSONObject();

        //先这样
        jsonObject.put("title",paper.getTitle());
        jsonObject.put("author",);
        jsonObject.put("keyword",);
        jsonObject.put("abstract",);
        jsonObject.put("link",);

        response.getWriter().print(jsonObject);
    }
}
