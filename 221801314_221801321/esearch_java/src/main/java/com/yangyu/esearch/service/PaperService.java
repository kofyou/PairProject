package com.yangyu.esearch.service;

import com.yangyu.esearch.entity.Paper;
import com.yangyu.esearch.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.service.PaperService
 * @Description : PaperService类
 * @date : 2021-03-24 22:18:26
 * Copyright  2021 user. All rights reserved.
 */
@Service
public class PaperService {

    @Autowired
    private PaperMapper paperMapper;

    //新增论文
    public int createPaper(Paper paper)
    {
        return paperMapper.createPaper(paper);
    }

    //查询所有论文
    public List<Paper> queryAll()
    {
        return paperMapper.queryAll();
    }

    //根据标题模糊查询论文，同时可以限定会议、年份，分页查询
    public List<Paper> selectTitle(String title, String source, String years, Integer address)
    {
        return paperMapper.selectTitle(title, source, years, address);
    }

    //根据会议查询论文
    public List<Paper> selectSource(String source)
    {
        return paperMapper.selectSource(source);
    }

    //根据年份查询论文
    public List<Paper> selectYears(String years)
    {
        return paperMapper.selectYears(years);
    }

    //根据关键词查询论文
    public List<Paper> selectKeyword(String keyword, Integer address)
    {
        return paperMapper.selectKeyword(keyword, address);
    }

    //根据关键词及年份查询论文
    public List<Paper> selectKeywordYear(String years, String keyword)
    {
        return paperMapper.selectKeywordYear(years, keyword);
    }

    //根据标题模糊查询论文，同时可以限定会议、年份
    public List<Paper> selectNumber(String title, String source, String years)
    {
        return paperMapper.selectNumber(title, source, years);
    }
}
