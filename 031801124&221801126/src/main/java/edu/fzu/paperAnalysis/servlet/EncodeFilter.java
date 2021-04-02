package edu.fzu.paperAnalysis.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodeFilter implements Filter {

    private String encode = "utf-8";


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) {

    }

}
