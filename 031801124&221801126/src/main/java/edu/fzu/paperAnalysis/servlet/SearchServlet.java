package edu.fzu.paperAnalysis.servlet;

import edu.fzu.paperAnalysis.dao.PaperDao;
import edu.fzu.paperAnalysis.dao.PaperDaoImpl;
import edu.fzu.paperAnalysis.service.PaperService;
import edu.fzu.paperAnalysis.service.PaperServiceImpl;
import edu.fzu.paperAnalysis.vo.Paper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SearchServlet
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/3/26 20:29
 * @Version 1.0
 **/
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");
        switch (type) {
            case "1": // 保存

                break;
            case "2": // 编辑

                break;
            case "3": // 删除

                break;
            default: // 查询
                queryPapers(req, resp);
        }
    }

    public void queryPapers(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PaperService paperService=new PaperServiceImpl();
            Paper tempPaper=new Paper();
            tempPaper.setPaperTitle(req.getParameter("pTitle"));
            tempPaper.setPaperReleasetime(req.getParameter("PaperReleasetime"));
            String pageNum = req.getParameter("pageNum");
            String changeNum = req.getParameter("changeNum");
            // pl每页显示的记录行数， pn当前页码，cn（上一页、下一页、查询），tn总的记录数
            int pl = 10, pn = 1, cn = 0, tn = paperService.queryNumber(tempPaper);
            // tp总的页数
            int tp = tn / 10 + (tn % 10 == 0 ? 0 : 1);
            // 当根据查询条件查询结果为空时，总页数默认为1页
            if (tp == 0) {
                tp = 1;
            }
            if (pageNum != null && !"".equals(pageNum)) {
                pn = Integer.parseInt(pageNum);
            }
            if (changeNum != null && !"".equals(changeNum)) {
                cn = Integer.parseInt(changeNum);
            }

            if (!(pn == 1 && cn == -1) && !(pn == tp && cn == 1)) {
                pn = pn + cn;
            }

            if (pn > tp) {
                pn = tp;
            }

            List<Paper> list = paperService.queryPapers(tempPaper, pn, pl);
            req.setAttribute("paper", tempPaper);
            req.setAttribute("pageNum", pn);
            req.setAttribute("totalPageNum", tp);
            req.setAttribute("totalNum", tn);
            req.setAttribute("paperList2", list);
            req.getRequestDispatcher("paperList.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
