package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("Button");
        switch (type) {
            case "ECCV":
                request.getRequestDispatcher("ECCV.jsp").forward(request, response);
            case "ICCV":
                request.getRequestDispatcher("ICCV.jsp").forward(request, response);
            case "CVPR":
                request.getRequestDispatcher("CVPR.jsp").forward(request, response);
        }
    }
}
