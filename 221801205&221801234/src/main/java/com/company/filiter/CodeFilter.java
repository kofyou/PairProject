package com.company.filiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //System.out.println("编码过滤器");
        //request.setCharacterEncoding("utf-8");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse r = (HttpServletResponse) response;
        //System.out.println(req.getRequestURL());
        if(!req.getRequestURI().contains("html")&&!req.getRequestURI().contains("css")
        &&!req.getRequestURI().contains("js")){
            r.setCharacterEncoding("utf-8");
        }

        //response.setCharacterEncoding("utf-8");
        
//        System.out.println(response.getCharacterEncoding());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
