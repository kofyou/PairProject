package com.pair.papercrawler.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Paper> getPaperByAuthorName(String author, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Paper> papers = authorsMapper.selectPaperByAuthorName(author);
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(papers);
        return pageInfo;

//        return authorsMapper.selectPaperByAuthorName(author);
    }

    @Override
    public String[] getAuthorByPaperId(Integer paperId) {
        return authorsMapper.selectAuthorByPaperId(paperId);
    }

}
