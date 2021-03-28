package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import bean.KeywordBean;
import bean.PaperBean;
import bean.PaperKeywordBean;
import dao.KeywordDao;
import dao.PaperDao;
import dao.PaperKeywordDao;

/**
 * Servlet implementation class PaperListByTitle
 */
@WebServlet("/PaperListByTitle")
public class PaperListByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqString = request.getReader().readLine();
		
		JSONObject reqJson = JSONObject.parseObject(reqString);
		String name = reqJson.getString("title");
		
		PaperDao paperDao = new PaperDao();
		LinkedList<PaperBean> paperList = paperDao.searchPaperListByName(name);
		//LinkedList<PaperBean> paperList = paperDao.searchPaperListByName("Accurate");
		//System.out.println(paperList.size());
		PaperKeywordDao paperKeywordDao = new PaperKeywordDao();
		JSONArray ja = new JSONArray();
		for (PaperBean paper:paperList) {
			JSONObject jo = new JSONObject();
			jo.put("name", paper.getName());
			jo.put("meeting", paper.getMeeting());
			jo.put("year", paper.getYear());
			LinkedList<PaperKeywordBean> keywordList = paperKeywordDao.searchKeywordListByName(paper.getName());
			
			//System.out.println(keywordList.size());
			String keywords = "";
			int cnt = 0;
			for (PaperKeywordBean keyword:keywordList) {
				keywords += keyword.getKeyword();
				keywords += ",";
				cnt ++;
				if (cnt == 2) break;
			}
			if (keywords.length() != 0)
				keywords = keywords.substring(0, keywords.length()-1);
			jo.put("keywords", keywords);
			ja.add(jo);
		}
		
	}

}
