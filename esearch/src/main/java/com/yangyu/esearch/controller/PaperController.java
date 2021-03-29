package com.yangyu.esearch.controller;

import com.yangyu.esearch.entity.Paper;
import com.yangyu.esearch.service.PaperService;
import com.yangyu.esearch.untils.ReadPaper;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.controller.PaperController
 * @Description : 类描述
 * @date : 2021-03-23 22:28:28
 * Copyright  2021 user. All rights reserved.
 */
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @GetMapping("query")
    public List<Paper> query()
    {
        return paperService.queryAll();
    }

    @GetMapping("title")
    public List<Paper> selectTitle(String title, Integer address)
    {
        return paperService.selectTitle(title, address*5);
    }

    @GetMapping("source")
    public List<Paper> selectSource(@ApiParam("顶会名称") String source)
    {
        return paperService.selectSource(source);
    }

    @GetMapping("years")
    public List<Paper> selectYears(@ApiParam("会议年份") String years)
    {
        return paperService.selectYears(years);
    }

    @GetMapping("keyword")
    public List<Paper> selectKeyword(@ApiParam("关键词") String keyword)
    {
        return paperService.selectKeyword(keyword);
    }

    @GetMapping("read")
    public void read()
    {
        ReadPaper readPaper = new ReadPaper();
        List<Paper> papers;

        papers = readPaper.readCVPR();//读取本地CVPR论文
        papers.clear();//清空list列表
        papers = readPaper.readCVPR();
        for (Paper paper : papers)
        {
            paperService.createPaper(paper);
        }
        papers.clear();
        papers = readPaper.readECCV();//读取本地ECCV论文
        for (Paper paper : papers)
        {
            paperService.createPaper(paper);
        }
        papers.clear();
        papers = readPaper.readICCV();////读取本地ICCV论文
        for (Paper paper : papers)
        {
            paperService.createPaper(paper);
        }
    }
}
