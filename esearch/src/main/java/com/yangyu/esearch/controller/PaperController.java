package com.yangyu.esearch.controller;

import com.yangyu.esearch.entity.Paper;
import com.yangyu.esearch.service.PaperService;
import com.yangyu.esearch.untils.ReadPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.controller.PaperController
 * @Description : PaperController类
 * @date : 2021-03-23 22:28:28
 * Copyright  2021 user. All rights reserved.
 */
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    //@GetMapping("query")
    public List<Paper> query()
    {
        return paperService.queryAll();
    }

    @GetMapping("title")
    public List<Paper> selectTitle(String title, String source, String years, Integer address)
    {
        return paperService.selectTitle(title, source, years, address*5);
    }

    @GetMapping("source")
    public List<Paper> selectSource(String source)
    {
        return paperService.selectSource(source);
    }

    @GetMapping("years")
    public List<Paper> selectYears(String years)
    {
        return paperService.selectYears(years);
    }

    @GetMapping("keyword")
    public List<Paper> selectKeyword(String keyword, Integer address)
    {
        return paperService.selectKeyword(keyword, address);
    }

    @GetMapping("number")
    public int selectNumber(String title, String source, String years)
    {
        return paperService.selectNumber(title, source, years).size();
    }

}
