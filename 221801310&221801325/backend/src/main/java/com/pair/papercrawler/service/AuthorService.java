package com.pair.papercrawler.service;

import com.github.pagehelper.PageInfo;
import com.pair.papercrawler.models.Author;
import com.pair.papercrawler.models.Paper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    PageInfo<Paper> getPaperByAuthorName(String author, Integer pageNum, Integer pageSize);

    String[] getAuthorByPaperId(Integer paperId);

}
