package com.cs.PairWork1.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cs.PairWork1.Dao.Paper;
import com.cs.PairWork1.Service.PaperListServiceImpl;
import com.cs.PairWork1.Utils.GetJson;

public class PaperListController implements Controller {

	/*private PaperListServiceImpl plsi;
	
	public void setPaperListServiceImpl(PaperListServiceImpl plsi) {
		this.plsi=plsi;
	}*/
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.setHeader("Content-type", "text/json;charset=UTF-8");
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		/*String s="F://2000_3-D model construction using range and image data.json";
		GetJson gj= new GetJson();
		gj.readJsonFile(s);
		String s1=gj.getAbout();
		String s2=gj.getId();
		String s3=gj.getKeywords();
		//String s3="keyword";
		String s4=gj.getUrl();
		String s5=gj.getTitle();
		String s6=s2+s5+s4+s1+s3;
		if(s6.isEmpty()) {
			System.out.println("s6 is not found");
		}
		else {
			System.out.println(s6);
		}*/
		
		JSONArray jsonArray= new JSONArray();
		PaperListServiceImpl p =new PaperListServiceImpl();
		
		
		/*for(int i = 0 ; i < p1.size() ; i++) {
			  System.out.println(p1.get(i).getAbout()+"from paperListController.do");
		}*/
		jsonArray.addAll(p.listAll());
		byte[] retArr = jsonArray.toJSONString().getBytes();
		arg1.getOutputStream().write(retArr);
		return null;
	}

}
