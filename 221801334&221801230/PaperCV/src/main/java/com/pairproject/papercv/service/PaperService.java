package com.pairproject.papercv.service;

import com.pairproject.papercv.model.Paper;

import java.util.List;

/**
 * 论文业务逻辑
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
     * 通过论文标题查询论文（模糊查询）
     *
     * @param title 论文标题
     * @return 论文列表
     */
    List<Paper> getPaper(String title);

    /**
     * 通过关键词查询论文
     *
     * @param keyWord 关键词
     * @return
     */
    List<Paper> getPaperByKey(String keyWord);

}
