package com.yrc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yrc.mapper.PaperMapper;
import com.yrc.pojo.GJC;
import com.yrc.pojo.paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class Test {
    @Autowired
    private PaperMapper paperMapper;

    @RequestMapping(value = "/query_papers")
    public String query_papers(Model model) {
        List<paper> papers = paperMapper.querypaperList();
        for (paper pa : papers) {
            System.out.println(pa);
        }
        model.addAttribute("paperList",papers);

        return "lunwen/论文列表";
    }


    @RequestMapping({"/sss"})
    public String toIndex (){
        return "lunwen/论文查询";
    }

    @RequestMapping(value = "/search")
    public String search(Model model,@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                          @RequestParam("gjc") String gjc) {
        PageHelper.startPage(pageNum,pageSize);
        System.out.println(gjc);
        PageInfo pageInfo1=new PageInfo(paperMapper.search_by_gjc(gjc));
        model.addAttribute("pageInfo1",pageInfo1);
        model.addAttribute("gjc",gjc);
        return "lunwen/list1";
    }



    @RequestMapping("/manageMember")
    public String manageMember(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(paperMapper.querypaperList());
        model.addAttribute("pageInfo",pageInfo);
        return "lunwen/论文列表";
    }

    @RequestMapping("/delete/paper/{id}")
    public String delete(HttpSession session, Model model,@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         @PathVariable("id") int id){
        paperMapper.delete_paper(id);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(paperMapper.querypaperList());
        model.addAttribute("pageInfo",pageInfo);
        return "lunwen/论文列表";
    }


    @RequestMapping(value = "/CVPR")
    public String CVPR(Model model) {
        return "lunwen/charts1";

    }

    @RequestMapping(value = "/ECCV")
    public String ECCV(Model model) {
        return "lunwen/charts2";

    }

    @RequestMapping(value = "/ICCV")
    public String ICCV(Model model) {
        return "lunwen/charts3";

    }
}
