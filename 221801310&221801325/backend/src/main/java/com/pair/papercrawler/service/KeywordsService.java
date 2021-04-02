package com.pair.papercrawler.service;

import com.github.pagehelper.PageInfo;
import com.pair.papercrawler.models.Paper;

import java.util.List;

public interface KeywordsService {


    PageInfo<Paper> getPaperByKeyword(String keyword, Integer pageNum, Integer pageSize);


    List<String> getKeywordByPaperId(Integer paperId);

}
