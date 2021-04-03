package com.yangyu.esearch.mapper;

import com.yangyu.esearch.entity.Paper;

import java.util.List;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.mapper.PaperMapper
 * @Description : PaperMapper的接口声明
 * @date : 2021-03-23 21:12:05
 * Copyright  2021 user. All rights reserved.
 */
public interface PaperMapper {

    int createPaper(Paper paper);
    List<Paper> queryAll();
    List<Paper> selectTitle(String title,String source, String years, Integer address);
    List<Paper> selectSource(String source);
    List<Paper> selectYears(String years);
    List<Paper> selectKeyword(String keyword, Integer address);
    List<Paper> selectKeywordYear(String years, String keyword);
    List<Paper> selectNumber(String title,String source, String years);
}
