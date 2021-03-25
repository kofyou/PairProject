package com.pairproject.papercv.service.impl;

import com.pairproject.papercv.dao.PaperMapper;
import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 通过题目查询论文，论文不存在则爬取数据再返回
     *
     * @param title 论文题目
     * @return 论文
     */
    @Override
    public Paper getPaper(String title) {
        return paperMapper.selectByTitle(title);
    }

}
