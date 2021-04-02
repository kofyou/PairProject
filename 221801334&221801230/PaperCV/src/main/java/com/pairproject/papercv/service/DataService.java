package com.pairproject.papercv.service;

import com.pairproject.papercv.model.Word;

import java.util.List;
import java.util.Map;

/**
 * 数据业务处理
 *
 * @date 2021/03/25
 */
public interface DataService {

    /**
     * 获取top500热词
     *
     * @return
     */
    List<Map.Entry<String, Integer>> getTop500();

    /**
     * 获取热词趋势
     *
     * @param meeting
     * @param years
     * @return
     */
    List<Word> getTrendWord(String meeting, List<String> years);

    /**
     * 获取论文数量
     *
     * @param meeting
     * @return
     */
    int getPaperCount(String meeting);
}
