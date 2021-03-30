package com.pair.papercrawler.service;

import com.pair.papercrawler.models.Paper;

import java.util.List;

public interface PapersService {
    void deletePaperByPaperId(String paperId);
    List<Paper> selectPaperByTitle(String title);
    List<Paper> selectPaperByMagazine(String magazine);
    Paper selectPaperByPaperId(Integer paperId);
    List<Paper> selectAll();
    List<Paper> selectPaperByAbstracts(String abstracts);
}
