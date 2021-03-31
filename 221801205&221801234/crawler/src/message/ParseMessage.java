package com.company.message;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.bean.KeywordBean;
import com.company.bean.PaperBean;
import com.company.bean.PaperKeywordBean;
import com.company.dao.KeywordDao;
import com.company.dao.PaperDao;
import com.company.dao.PaperKeywordDao;


import java.util.LinkedList;

public class ParseMessage {
	
	public void getMessageICCVandCVPR(String json, String meetingName) {
		try {
			
		json = json.trim();
		json = json.substring(0, json.length()-1);
		PaperBean paperBean = new PaperBean();
		PaperDao paperDao = new PaperDao();
		JSONObject jo = JSONObject.parseObject(json);
		
		paperBean.setName(jo.getString("title"));
		paperBean.setYear(jo.getString("publicationYear"));
		paperBean.setMeeting(meetingName);
		paperBean.setAbstractt(jo.getString("abstract"));
		paperBean.setUrl(jo.getString("doiLink"));
		paperBean.setAccesstimes(0);
		paperDao.insertOne(paperBean);
		
		
		LinkedList<KeywordBean> keywordBeans = new LinkedList<KeywordBean>();
		KeywordDao keywordDao = new KeywordDao();
		JSONArray ja = jo.getJSONArray("keywords");
		Object[]keywords = ja.getJSONObject(0).getJSONArray("kwd").toArray();
		for (Object keyword:keywords) {
			KeywordBean keywordBean = new KeywordBean();
			//System.out.println(keyword);
			//System.out.println((String)keyword);
			keywordBean.setKeyword((String)keyword);
			keywordBean.setAppeartimes(1);
			keywordBeans.add(keywordBean);
		}
		keywordDao.insertOrUpdateAll(keywordBeans);
		
		LinkedList<PaperKeywordBean> paperKeywordBeans = new LinkedList<PaperKeywordBean>();
		PaperKeywordDao paperKeywordDao = new PaperKeywordDao();
		
		for (KeywordBean keywordBean:keywordBeans) {
			PaperKeywordBean paperKeywordBean = new PaperKeywordBean();
			paperKeywordBean.setName(jo.getString("title"));
			paperKeywordBean.setKeyword(keywordBean.getKeyword());
			paperKeywordBeans.add(paperKeywordBean);
		}
		paperKeywordDao.insertAll(paperKeywordBeans);
		
		}catch(Exception e){
			System.out.println(json);
		}
	}
	
	public void getMessageECCV(String json) {
		try {
		json = json.trim();
		//System.out.println(json);
		PaperBean paperBean = new PaperBean();
		PaperDao paperDao = new PaperDao();
		JSONObject jo = JSONObject.parseObject(json);
		String meetingAndYear = jo.getString("会议和年份");
		String[] ss = meetingAndYear.split(" ");
		
		paperBean.setName(jo.getString("论文名称"));
		paperBean.setYear(ss[1]);
		paperBean.setMeeting(ss[0]);
		paperBean.setAbstractt(jo.getString("摘要"));
		paperBean.setUrl(jo.getString("原文链接"));
		paperBean.setAccesstimes(0);
		paperDao.insertOne(paperBean);
		
		
		LinkedList<KeywordBean> keywordBeans = new LinkedList<KeywordBean>();
		KeywordDao keywordDao = new KeywordDao(); 
		JSONArray ja = jo.getJSONArray("关键词");
		Object[]keywords = ja.toArray();
		for (Object keyword:keywords) {
			KeywordBean keywordBean = new KeywordBean();
			//System.out.println(keyword);
			//System.out.println((String)keyword);
			keywordBean.setKeyword((String)keyword);
			keywordBean.setAppeartimes(1);
			keywordBeans.add(keywordBean);
		}
		keywordDao.insertOrUpdateAll(keywordBeans);
		
		LinkedList<PaperKeywordBean> paperKeywordBeans = new LinkedList<PaperKeywordBean>();
		PaperKeywordDao paperKeywordDao = new PaperKeywordDao();
		
		for (KeywordBean keywordBean:keywordBeans) {
			PaperKeywordBean paperKeywordBean = new PaperKeywordBean();
			paperKeywordBean.setName(jo.getString("论文名称"));
			paperKeywordBean.setKeyword(keywordBean.getKeyword());
			paperKeywordBeans.add(paperKeywordBean);
		}
		paperKeywordDao.insertAll(paperKeywordBeans);

		}catch(Exception e){
			System.out.println(json);
		}
	}
		
}
