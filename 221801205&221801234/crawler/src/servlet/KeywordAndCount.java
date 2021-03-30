package com.company.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.bean.*;
import com.company.dao.KeywordDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Servlet implementation class KeywordAndCount
 */
@WebServlet("/KeywordAndCount")
public class KeywordAndCount extends HttpServlet {
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
		LinkedList<KeywordBean> keywordList = new KeywordDao().searchMaxTen(10);
		JSONArray ja = new JSONArray();
		for (KeywordBean keyword:keywordList) {
			JSONObject jo = new JSONObject();
			jo.put("keyword", keyword.getKeyword());
			jo.put("appeartimes", keyword.getAppeartimes());
			ja.add(jo);
		}
		response.getWriter().write(ja.toJSONString());
	}

}
