package com.pair.papercrawler.service.impl;

import com.pair.papercrawler.dao.AuthorsMapper;
import com.pair.papercrawler.models.Author;
import com.pair.papercrawler.models.Paper;
import com.pair.papercrawler.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorsMapper authorsMapper;

    @Override
    public List<Paper> getPaperByAuthorName(String author) {
        return authorsMapper.selectPaperByAuthorName(author);
    }

    @Override
    public String[] getAuthorByPaperId(Integer paperId) {
        return authorsMapper.selectAuthorByPaperId(paperId);
    }

}
