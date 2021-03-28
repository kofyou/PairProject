package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/analyze")
public class KeyWordServlet extends HttpServlet{

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "select * from topKeyWord";
        List <String> keyWordList = new ArrayList<>();

        keyWordList.add("feature");
        keyWordList.add("modeling");
        keyWordList.add("recognition");
        keyWordList.add("detection");
        keyWordList.add("cameras");
        keyWordList.add("vision");
        keyWordList.add("training");
        keyWordList.add("computer");
        keyWordList.add("analysis");
        keyWordList.add("image");

        /*try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                String keyWord = rs.getString("keyWord");
                keyWordList.add(keyWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
       //req.setAttribute("sql",sql);
        req.setAttribute("keyWordList",keyWordList);
        req.getRequestDispatcher("/analyze.jsp").forward(req,resp);
    }
}
