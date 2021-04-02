package com.company.servlet;


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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;

/**
 * Servlet implementation class PaperListDetailByTitle
 */
@WebServlet("/PaperListDetailByTitle")
public class PaperListDetailByTitle extends HttpServlet {
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
		System.out.println("name"+name);
		String decode = URLDecoder.decode(name, "utf-8");
//		System.out.println("decode"+decode);
		PaperDao paperDao = new PaperDao();
		PaperBean paper = paperDao.searchPaperByName(decode);
		//PaperBean paper = paperDao.searchPaperByName("3D human pose search using oriented cylinders");
		//System.out.println(paperList.size());
		PaperKeywordDao paperKeywordDao = new PaperKeywordDao();
		JSONObject jo = new JSONObject();
		jo.put("name", paper.getName());
		jo.put("meeting", paper.getMeeting());
		jo.put("year", paper.getYear());
		jo.put("url", paper.getUrl());
		jo.put("abstract", paper.getAbstractt());
		LinkedList<PaperKeywordBean> keywordList = paperKeywordDao.searchKeywordListByName(paper.getName());
			
			//System.out.println(keywordList.size());
		String keywords = "";
		int cnt = 0;
		for (PaperKeywordBean keyword:keywordList) {
			keywords += keyword.getKeyword();
			keywords += ",";
		}
		if (keywords.length() != 0)
			keywords = keywords.substring(0, keywords.length()-1);
		
		jo.put("keywords", keywords);

		response.getWriter().write(jo.toJSONString());
	}

}
