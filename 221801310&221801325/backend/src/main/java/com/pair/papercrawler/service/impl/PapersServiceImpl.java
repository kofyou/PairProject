package com.pair.papercrawler.service.impl;

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

    }

    @Override
    public List<Paper> selectPaperByTitle(String title) {
        return papersMapper.selectPaperByTitle("%" + title + "%");
    }

    @Override
    public List<Paper> selectPaperByAbstracts(String abstracts) {
        return papersMapper.selectPaperByAbstracts("%" + abstracts + "%");
    }

    @Override
    public List<Paper> selectPaperByMagazine(String magazine) {
        return papersMapper.selectPaperByMagazine(magazine);
    }


    @Override
    public Paper selectPaperByPaperId(Integer paperId) {
        return papersMapper.selectPaperByPaperId(paperId);
    }

    @Override
    public List<Paper> selectAll() {
        return papersMapper.selectAll();
    }


}
