package edu.fzu.postsearch.servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

public abstract class BaseBackServlet extends HttpServlet {

    public abstract String add(HttpServletRequest request, HttpServletResponse response);

    public abstract String delete(HttpServletRequest request, HttpServletResponse response);

    public abstract String edit(HttpServletRequest request, HttpServletResponse response);

    public abstract String update(HttpServletRequest request, HttpServletResponse response);

}
