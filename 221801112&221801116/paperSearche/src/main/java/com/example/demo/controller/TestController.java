package com.example.demo.controller;

import com.example.demo.service.serviceImpl.LimitPaperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 21:54 2021/3/22
 */
@Controller
public class TestController {

    @Autowired
    LimitPaperImpl limitPaperService;

    @GetMapping("/left")
    public String getLeft() {
        return "paperlist";
    }

    @GetMapping("/ciyun")
    public String getCi() {
        return "ciyuntest";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

    @PostMapping("/updateTest")
    public String postUpdate(HttpServletRequest request) {
        String title = request.getParameter("textarea-title");
        System.out.println(title);
        return "test";
    }

    @PostMapping("/deleteTest")
    public String postDelete(HttpServletRequest request) {
        String paperId = request.getParameter("paperId");
        Integer result = limitPaperService.deletePaper(Integer.parseInt(paperId));

        if(result == 1){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

        return "test";
    }
}
