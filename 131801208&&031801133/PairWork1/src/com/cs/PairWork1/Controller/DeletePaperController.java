package com.cs.PairWork1.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.cs.PairWork1.Service.PaperListServiceImpl;

public class DeletePaperController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		arg1.setHeader("Content-type", "text/html;charset=UTF-8");
		String title= arg0.getParameter("Title");
		if( (null==title)||title.trim().equals("")) {
			arg1.getOutputStream().write("-1".getBytes());
		}
		PaperListServiceImpl p =new PaperListServiceImpl();
		int ret= p.delete(title);
		arg1.getOutputStream().write(String.valueOf(ret).getBytes());
		return null;
	}

}
