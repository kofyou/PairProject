package com.company.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.dao.PaperKeywordDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet implementation class MeetingWordTrend
 */
@WebServlet("/MeetingWordTrend")
public class MeetingWordTrend extends HttpServlet {
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
		String meeting = reqJson.getString("meeting");
		String resString = null;
		if (meeting.equals("ECCV")) {
			resString= doECCV();
		}
		else if (meeting.equals("CVPR")) {
			resString = doCVPR();
		}
		else if (meeting.equals("ICCV")) {
			resString = doICCV();
		}
		response.getWriter().write(resString);
	}
	
	private String doECCV() {
		String meeting = "ECCV";
		String[]years = {"2016", "2018", "2020"};
		
		HashMap[]maps=new HashMap[3];

		PaperKeywordDao dao = new PaperKeywordDao();
		JSONArray jaOutside = new JSONArray();
		String[]keywords = dao.searchTopKeywordByMnY(meeting, "2020");
		for (int i = 0; i < 3; i ++) {
			
			maps[i] = dao.searchYnCByMnKnYC(meeting, keywords[i], 3);
			JSONObject joOutside = new JSONObject();
			joOutside.put("keyword", keywords[i]);
			JSONArray jaInside = new JSONArray();
			for (int j = 0; j < 3; j ++) {
				JSONObject joInside = new JSONObject();
				joInside.put("year", years[j]);
				System.out.println(maps[i].get(years[j]));
				joInside.put("count", maps[i].get(years[j])==null?0:maps[i].get(years[j]));
				jaInside.add(joInside);
			}
			joOutside.put("yearAndCount", jaInside);
			jaOutside.add(joOutside);
		}
		return jaOutside.toJSONString();
		
	}
	
	private String doICCV() {
		String meeting = "ICCV";
		String[]years = {"2015", "2017", "2019"};
		String[]keywords = new String[3];
		HashMap[]maps=new HashMap[3];
		PaperKeywordDao dao = new PaperKeywordDao();
		JSONArray jaOutside = new JSONArray();
		keywords = dao.searchTopKeywordByMnY(meeting, "2019");	
		for (int i = 0; i < 3; i ++) {
			maps[i] = dao.searchYnCByMnKnYC(meeting, keywords[i], 3);
			JSONObject joOutside = new JSONObject();
			joOutside.put("keyword", keywords[i]);
			JSONArray jaInside = new JSONArray();
			for (int j = 0; j < 3; j ++) {
				JSONObject joInside = new JSONObject();
				joInside.put("year", years[j]);
				System.out.println(maps[i].get(years[j]));
				joInside.put("count", maps[i].get(years[j])==null?0:maps[i].get(years[j]));
				jaInside.add(joInside);
			}
			joOutside.put("yearAndCount", jaInside);
			jaOutside.add(joOutside);
		}
		return jaOutside.toJSONString();
	}
	
	private String doCVPR() {
		String meeting = "CVPR";
		String[]years = {"2018", "2019", "2020"};
		String[]keywords = new String[3];
		HashMap[]maps=new HashMap[3];
		PaperKeywordDao dao = new PaperKeywordDao();
		JSONArray jaOutside = new JSONArray();
		keywords = dao.searchTopKeywordByMnY(meeting, "2020");	
		for (int i = 0; i < 3; i ++) {
			maps[i] = dao.searchYnCByMnKnYC(meeting, keywords[i], 3);
			JSONObject joOutside = new JSONObject();
			joOutside.put("keyword", keywords[i]);
			JSONArray jaInside = new JSONArray();
			for (int j = 0; j < 3; j ++) {
				JSONObject joInside = new JSONObject();
				joInside.put("year", years[j]);
				joInside.put("count", maps[i].get(years[j])==null?0:maps[i].get(years[j]));
				System.out.println(maps[i].get(years[j]));
				jaInside.add(joInside);
			}
			joOutside.put("yearAndCount", jaInside);
			jaOutside.add(joOutside);
		}
		return jaOutside.toJSONString();
		
	}

}
