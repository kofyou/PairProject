package com.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.getInfo.GetJson;

public class PaperListConTroller implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.setHeader("Content-type", "text/json;charset=UTF-8");
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		String s="F://2000_3-D model construction using range and image data.json";
		GetJson gj= new GetJson();
		gj.readJsonFile(s);
		String s1=gj.getAbout();
		String s2=gj.getId();
		//String s3=gj.getKeywords();
		String s3="keyword";
		String s4=gj.getUrl();
		String s5=gj.getTitle();
		String s6=s2+s5+s4+s1+s3;
		byte[] retArr = s1.getBytes(s6);
		arg1.getOutputStream().write(retArr);
		return null;
	}

}
