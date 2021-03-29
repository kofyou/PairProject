package com.yangyu.esearch.controller;

import com.yangyu.esearch.entity.Keyword;
import com.yangyu.esearch.service.KeywordService;
import com.yangyu.esearch.service.PaperService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.controller.KeywordController
 * @Description : KeywordController类
 * @date : 2021-03-28 16:29:35
 * Copyright  2021 user. All rights reserved.
 */

@RestController
@RequestMapping("/word")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private PaperService paperService;

    @GetMapping("top")
    public List<Keyword> topWord()
    {
        return keywordService.wordList();
    }

    @GetMapping("meet")
    public List<Keyword> meetTop(@ApiParam("顶会名称") String source)
    {
        return keywordService.topWord(paperService.selectSource(source));
    }

    @GetMapping("year")
    public int selectKeywordYear(String years, String keyword)
    {
        return paperService.selectKeywordYear(years, keyword).size();
    }
}
