package com.example.demo.controller;


import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperResponsBody;
import com.example.demo.service.serviceImpl.LimitPaperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 9:51 2021/3/24
 */
@Controller
public class LimitPaperController {

    @Autowired
    LimitPaperImpl limitPaperService;

    @RequestMapping("/getLimitPaper")
    @ResponseBody
    public PaperResponsBody getLimitPaper(@RequestParam(defaultValue = "1") String start, @RequestParam(defaultValue = "8") String limit)
    {
        List<Paper> paperList = limitPaperService.getLimitPaper(Integer.parseInt(start),Integer.parseInt(limit));
        for(Paper paper:paperList) {
            if(paper.getPersistentLink().contains("https:")){

            }else {
                paper.setPersistentLink("https://ieeexplore.ieee.org"+paper.getPersistentLink());
            }
        }

        PaperResponsBody paperResponsBody=new PaperResponsBody();
        paperResponsBody.setCode("0");
        paperResponsBody.setMsg("成功");

        Integer count = limitPaperService.getCount();
        paperResponsBody.setCount(count);
        paperResponsBody.setData(paperList);
        return paperResponsBody;
    }

    @RequestMapping("/searchByKeyWord")
    @ResponseBody
    public PaperResponsBody getLimitPaper(@RequestParam(defaultValue = "") String keywords,@RequestParam(defaultValue = "1") String start, @RequestParam(defaultValue = "8") String limit)
    {
        List<Paper> paperList = limitPaperService.searchByKeyWords(keywords,Integer.parseInt(start),Integer.parseInt(limit));
        for(Paper paper:paperList) {
            if(paper.getPersistentLink().contains("https:")){

            }else {
                paper.setPersistentLink("https://ieeexplore.ieee.org"+paper.getPersistentLink());
            }
        }
        PaperResponsBody paperResponsBody=new PaperResponsBody();
        paperResponsBody.setCode("0");
        paperResponsBody.setMsg("成功");
        Integer count = limitPaperService.getCountS(keywords);
        paperResponsBody.setCount(count);
        paperResponsBody.setData(paperList);
        return paperResponsBody;
    }

    @PostMapping("/delete")
    public String postDelete(HttpServletRequest request) {
        String paperId = request.getParameter("paperId");
        Integer result = limitPaperService.deletePaper(Integer.parseInt(paperId));

        if(result == 1){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

        return "paperlist";
    }

    @PostMapping("/update")
    public String postUpdate(HttpServletRequest request) {
        String paperId = request.getParameter("paperId");
        String title = request.getParameter("textarea-title");
        String year = request.getParameter("textarea-year");
        String link = request.getParameter("textarea-link");
        String abstrac = request.getParameter("textarea-abstract");
        String key = request.getParameter("textarea-key");

        Integer result = limitPaperService.updatePaper(Integer.parseInt(paperId),title,key,abstrac,link,year);
        if(result == 1){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        return "paperlist";
    }

    @PostMapping("/insert")
    public String postInsert(HttpServletRequest request) {
        String title = request.getParameter("textarea-title");
        String year = request.getParameter("textarea-year");
        String link = request.getParameter("textarea-link");
        String abstrac = request.getParameter("textarea-abstract");
        String key = request.getParameter("textarea-key");

        Integer result = limitPaperService.insertPaper(title,key,abstrac,link,year);
        if(result == 1){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
        return "paperlist";
    }

}
