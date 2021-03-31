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

public class PaperListController implements Controller {

	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.setHeader("Content-type", "text/json;charset=UTF-8");
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		//String s="F://2000_3-D model construction using range and image data.json";
		//String value=arg0.getParameter("value");
		
		JSONArray jsonArray= new JSONArray();
		PaperListServiceImpl p =new PaperListServiceImpl();
		//File file =null;
		//if(value.equals("ICCV")) {
		File	 file = new File("F:\\论文数据\\ICCV（2001年至2019年，3196篇）");
		//}
		//if(value.equals("ECCV")) {
		File	 file2 = new File("F:\\论文数据\\ECCV（2016至2020，3033份）");
		//}
		//if(value.equals("CVPR")) {
		File	file3 = new File("F:\\论文数据\\CVPR（2000年至2020年，6916篇）");
		//}

		
		jsonArray.addAll(p.listAll());
//		jsonArray.addAll(p.getDirectory2(file2));
//		jsonArray.addAll(p.getDirectory(file3));
	
		
		byte[] retArr = jsonArray.toJSONString().getBytes();
		arg1.getOutputStream().write(retArr);
		return null;
	}

}
