package com.pairproject.papercv.controller;

import com.pairproject.papercv.dao.PaperMapper;
import com.pairproject.papercv.model.Word;
import com.pairproject.papercv.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    @Autowired
    private DataService dataService;

    @GetMapping("/top")
    public List<Word> top() {
//        return dataService.getHotWord("ECCV", Arrays.asList("2016", "2018", "2020"));
//        return dataService.getHotWord("ICCV", Arrays.asList("2011", "2013", "2015", "2017", "2019"));
        return dataService.getTrendWord("CVPR", Arrays.asList("2016", "2017", "2018", "2019", "2020"));
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

//    @GetMapping("/eccv")
//    public String addECCV() {
//        List<Paper> papers = FileUtil.readECCV();
//        for (Paper p : papers) {
//            Paper paper = paperMapper.selectByTitle(p.getTitle());
//            if (paper == null){
//                paperMapper.insert(p);
//            }
//        }
//        return "ok";
//    }

//    @GetMapping("/iccv")
//    public String addICCV() {
//        List<Paper> papers = FileUtil.readICCV();
//        for (Paper p : papers) {
//            Paper paper = paperMapper.selectByTitle(p.getTitle());
//            if (paper == null){
//                paperMapper.insert(p);
//            }
//        }
//        return "ok";
//    }

//    @GetMapping("/cvpr")
//    public String addCVPR() {
//        List<Paper> papers = FileUtil.readCVPR();
//        for (Paper p : papers) {
//            Paper paper = paperMapper.selectByTitle(p.getTitle());
//            if (paper == null){
//                paperMapper.insert(p);
//            }
//        }
//        return "ok";
//    }

}
