package com.pair.papercrawler.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pair.papercrawler.dao.PapersMapper;
import com.pair.papercrawler.models.Paper;
import com.pair.papercrawler.service.PapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PapersServiceImpl implements PapersService {
    @Autowired
    private PapersMapper papersMapper;

    @Override
    public void deletePaperByPaperId(Integer paperId) {
        papersMapper.deletePaperByPaperId(paperId);
    }

    @Override
    public PageInfo<Paper> selectPaperByTitle(String title,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Paper> papers = papersMapper.selectPaperByTitle("%" + title + "%");
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(papers);
        return pageInfo;
    }

    @Override
    public PageInfo<Paper> selectPaperByAbstracts(String abstracts,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Paper> papers = papersMapper.selectPaperByAbstracts("%" + abstracts + "%");
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(papers);
        return pageInfo;
    }

    @Override
    public PageInfo<Paper> selectPaperByMagazine(String magazine,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Paper> papers = papersMapper.selectPaperByMagazine(magazine);
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(papers);
        return pageInfo;
    }


    @Override
    public Paper selectPaperByPaperId(Integer paperId) {
        return papersMapper.selectPaperByPaperId(paperId);
    }

    @Override
    public PageInfo<Paper> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Paper> papers = papersMapper.selectAll();
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(papers);
        return pageInfo;
    }

}
