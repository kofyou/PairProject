package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/KWCServlet")
public class KWCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("button");
        switch (type) {
            case "button1":
                request.setAttribute("type", type);
                request.getRequestDispatcher("KWCSList.jsp").forward(request, response);
        }
    }
}
