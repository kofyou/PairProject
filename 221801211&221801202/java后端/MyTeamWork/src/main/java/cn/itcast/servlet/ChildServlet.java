package cn.itcast.servlet;

import cn.itcast.dao.KeynumDaoImpl;
import cn.itcast.domain.Keynum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/child")
public class ChildServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        KeynumDaoImpl keynumDao = new KeynumDaoImpl();
        List<Keynum> keynumList = new ArrayList<Keynum>();
        keynumList = keynumDao.selectkey();
        if(req.getParameter("p")!=null)
        {
            //req.setAttribute("keyword",req.getParameter("p"));
            req.getRequestDispatcher("homepage").forward(req,resp);
        }
        else {
            req.setAttribute("keynumList",keynumList);
            req.getRequestDispatcher("/front/child.jsp").forward(req,resp);
        }
    }
}
