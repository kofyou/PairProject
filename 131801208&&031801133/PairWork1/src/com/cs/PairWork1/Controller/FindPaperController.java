package com.cs.PairWork1.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSONArray;
import com.cs.PairWork1.Service.PaperListServiceImpl;

public class FindPaperController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String id=arg0.getParameter("id");
		if( (null==id)||id.trim().equals("")) {
			arg1.getOutputStream().write("根本没找到你要的".getBytes());
		}
		PaperListServiceImpl p =new PaperListServiceImpl();
		JSONArray jsonArray= new JSONArray();
		jsonArray.addAll(p.find(id));		
		byte[] retArr = jsonArray.toJSONString().getBytes();
		arg1.getOutputStream().write(retArr);
		return null;

	}

}
