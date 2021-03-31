package com.pair.papercrawler.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pair.papercrawler.dao.KeywordsMapper;
import com.pair.papercrawler.models.Paper;
import com.pair.papercrawler.service.KeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordsServiceImpl implements KeywordsService {

    @Autowired
    private KeywordsMapper keywordsMapper;


    /*** 
     * @Description: 通过keyword模糊查询
     * @Param:
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    @Override
    public PageInfo<Paper> getPaperByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Paper> papers = keywordsMapper.selectPaperByKeyword("%" + keyword + "%");
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(papers);
        return pageInfo;

//        return keywordsMapper.selectPaperByKeyword("%" + keyword + "%");
    }

    @Override
    public List<String> getKeywordByPaperId(Integer paperId) {
        return keywordsMapper.selectKeywordByPaperId(paperId);
    }

}
