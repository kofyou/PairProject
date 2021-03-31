package com.pair.papercrawler.service;

import com.github.pagehelper.PageInfo;
import com.pair.papercrawler.models.Paper;

import java.util.List;

public interface PapersService {
    void deletePaperByPaperId(Integer paperId);

    PageInfo<Paper> selectPaperByTitle(String title,Integer pageNum, Integer pageSize);

    PageInfo<Paper> selectPaperByMagazine(String magazine,Integer pageNum, Integer pageSize);

    Paper selectPaperByPaperId(Integer paperId);

    PageInfo<Paper> selectAll(Integer pageNum, Integer pageSize);

    PageInfo<Paper> selectPaperByAbstracts(String abstracts,Integer pageNum, Integer pageSize);
}
