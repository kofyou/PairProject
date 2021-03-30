package com.yangyu.esearch.controller;

import com.yangyu.esearch.entity.Keyword;
import com.yangyu.esearch.entity.WordYear;
import com.yangyu.esearch.entity.YearWord;
import com.yangyu.esearch.service.KeywordService;
import com.yangyu.esearch.service.PaperService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Keyword> meetTop( String source)
    {
        return keywordService.topWord(paperService.selectSource(source));
    }

    @PostMapping("year")
    public List<YearWord> selectKeywordYear(@RequestBody WordYear wordYear)
    {
        String []years = wordYear.getYears();
        String []keyword = wordYear.getKeyword();
        List<YearWord> yearWords = new LinkedList<>();
        for (String word : keyword)
        {
            YearWord yearWord = new YearWord();
            yearWord.setKeyword(word);
            int [] num = new int[years.length];
            for (int i = 0; i < years.length; i++)
            {
                num[i] = paperService.selectKeywordYear(years[i], word).size();
            }
            yearWord.setNum(num);
            yearWords.add(yearWord);
        }
        return yearWords;
    }
}
