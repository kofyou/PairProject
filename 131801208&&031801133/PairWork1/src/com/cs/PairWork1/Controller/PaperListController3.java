package com.cs.PairWork1.Controller;

import java.io.File;
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

public class PaperListController3 implements Controller {

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.setHeader("Content-type", "text/json;charset=UTF-8");
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		//String s="F://2000_3-D model construction using range and image data.json";
		
		
		JSONArray jsonArray= new JSONArray();
		PaperListServiceImpl p =new PaperListServiceImpl();
		
		File	file = new File("F:\\论文数据\\CVPR（2000年至2020年，6916篇）");
		
		jsonArray.addAll(p.getDirectory(file));
		byte[] retArr = jsonArray.toJSONString().getBytes();
		arg1.getOutputStream().write(retArr);
		return null;
	}

}