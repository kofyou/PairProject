package com.pair.papercrawler.service;

import com.pair.papercrawler.models.Author;
import com.pair.papercrawler.models.Paper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    List<Paper> getPaperByAuthorName(String author);

    String[] getAuthorByPaperId(Integer paperId);

}
