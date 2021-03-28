package com.pairproject.papercv.service.impl;

import com.pairproject.papercv.dao.PaperMapper;
import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 论文service实现类
 *
 * @date 2021/03/23
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    /**
     * 获取论文列表
     *
     * @return 论文列表
     */
    @Override
    public List<Paper> getAll() {
        return paperMapper.selectAll();
    }

    /**
     * 通过论文标题查询论文（模糊查询）
     *
     * @param title 论文题目
     * @return 论文列表
     */
    @Override
    public List<Paper> getPaper(String title) {
        return paperMapper.selectByTitle(title);
    }

    /**
     * 通过关键词查询论文
     *
     * @param keyWord 关键词
     * @return
     */
    @Override
    public List<Paper> getPaperByKey(String keyWord) {
        List<Paper> paperList = paperMapper.selectAll();
        List<Paper> res = new ArrayList<>();
        for (Paper paper : paperList) {
            for (String key : paper.getKeyWord().split(",")) {
                if (keyWord.equals(key)) {
                    res.add(paper);
                    break;
                }
            }
        }
        return res;
    }

}
