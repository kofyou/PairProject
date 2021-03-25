package com.pairproject.papercv.controller;

import com.pairproject.papercv.dao.PaperMapper;
import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试控制器
 *
 * @date 2021/03/22
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PaperMapper paperMapper;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/eccv")
    public String addECCV() {
        List<Paper> papers = FileUtil.readECCV();
        for (Paper p : papers) {
            Paper paper = paperMapper.selectByTitle(p.getTitle());
            if (paper == null){
                paperMapper.insert(p);
            }
        }
        return "ok";
    }

    @GetMapping("/iccv")
    public String addICCV() {
        List<Paper> papers = FileUtil.readICCV();
        for (Paper p : papers) {
            Paper paper = paperMapper.selectByTitle(p.getTitle());
            if (paper == null){
                paperMapper.insert(p);
            }
        }
        return "ok";
    }

}
