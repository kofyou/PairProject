package servlet;

import dao.WordDAO;
import dao.WordDAOImpl;
import pojo.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/ChartServlet")
public class ChartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WordDAO wordDAO = new WordDAOImpl();
        List<Word> list ;
        list = wordDAO.listGetByYear();

        String hotwords[];
        int cnt = 0;

        for (Word word : list) {
            String s = word.getKeywords();
            strCut(s);
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("Chart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static boolean isWord(String str) {//判断仅包含 字母\数字\空格\- 的字符串
        String regex = "^[a-z0-9A-Z -]+$";
        return str.matches(regex);
    }

    public static String[] strCut(String s) {//字符串切割
        String[] tempStr = null;//仅包含字母和数字的字符串数组
        int cnt = 0;//字符串个数计数
        Pattern pattern = Pattern.compile(",");
        String[] newStr = pattern.split(s);
        for(String ss : newStr) {
            if (isWord(ss)) {
                tempStr[cnt++] = ss;
            }
        }
        return tempStr;
    }
}
