package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/analyze")
public class KeyWordServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "select * from topKeyWord";
        List <String> keyWordList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String word = rs.getString("keyWord");
                int value = rs.getInt("frequency");
                keyWordList.add(word);
                valueList.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("keyWordList",keyWordList);
        req.setAttribute("valueList",valueList);
        req.getRequestDispatcher("/analyze.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        doPost(req,resp);
    }

}
