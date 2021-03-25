package edu.fzu.postsearch.servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

public abstract class BaseBackServlet extends HttpServlet {

    public abstract String add(HttpServletRequest request, HttpServletResponse response);

    public abstract String delete(HttpServletRequest request, HttpServletResponse response);

    public abstract String edit(HttpServletRequest request, HttpServletResponse response);

    public abstract String update(HttpServletRequest request, HttpServletResponse response);

}
