package com.company.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.bean.PaperBean;
import com.company.bean.PaperKeywordBean;
import com.company.dao.PaperDao;
import com.company.dao.PaperKeywordDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;

/**
 * Servlet implementation class PaperListByTitle
 */
@WebServlet(value="/PaperListByTitle")
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
			//String encode = URLEncoder.encode(paper.getName(), "utf-8");
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

		response.getWriter().write(ja.toJSONString());
		
	}

}
