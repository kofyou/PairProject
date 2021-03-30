package com.pair.papercrawler.service;

import com.pair.papercrawler.models.Paper;

import java.util.List;

public interface KeywordsService {


    List<Paper> getPaperByKeyword(String keyword);


    List<String> getKeywordByPaperId(Integer paperId);

}
