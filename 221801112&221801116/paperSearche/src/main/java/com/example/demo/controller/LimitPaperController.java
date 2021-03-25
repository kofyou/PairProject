package com.example.demo.controller;

import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperResponsBody;
import com.example.demo.service.serviceImpl.LimitPaperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        paperResponsBody.setCount(limitPaperService.getCountS(keywords)-3);
        paperResponsBody.setData(paperList);
        return paperResponsBody;
    }
}
