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

        PaperResponsBody paperResponsBody=new PaperResponsBody();
        paperResponsBody.setCode("0");
        paperResponsBody.setMsg("成功");
        paperResponsBody.setCount(limitPaperService.getCount()-3);
        paperResponsBody.setData(limitPaperService.getLimitPaper(Integer.parseInt(start),Integer.parseInt(limit)));
        return paperResponsBody;
    }

    @RequestMapping("/searchByKeyWord")
    @ResponseBody
    public PaperResponsBody getLimitPaper(@RequestParam(defaultValue = "") String keywords,@RequestParam(defaultValue = "1") String start, @RequestParam(defaultValue = "8") String limit)
    {
        List<Paper> paperList = limitPaperService.searchByKeyWords(keywords,Integer.parseInt(start),Integer.parseInt(limit));
        PaperResponsBody paperResponsBody=new PaperResponsBody();
        paperResponsBody.setCode("0");
        paperResponsBody.setMsg("成功");
        Integer count = limitPaperService.getCountS(keywords);
        if(count<=3){
            paperResponsBody.setCount(count);
        } else{
            paperResponsBody.setCount(count-3);
        }

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

}
