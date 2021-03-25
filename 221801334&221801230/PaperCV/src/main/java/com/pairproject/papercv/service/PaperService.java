package com.pairproject.papercv.service;

import com.pairproject.papercv.model.Paper;

import java.util.List;

/**
 * 论文service类
 *
 * @date 2021/03/23
 */
public interface PaperService {

    /**
     * 获取论文列表
     *
     * @return 论文列表
     */
    List<Paper> getAll();

    /**
     * 通过题目查询论文，论文不存在则爬取数据再返回
     *
     * @param title 论文题目
     * @return 论文
     */
    Paper getPaper(String title);

}
