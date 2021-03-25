package com.pairproject.papercv.service;

import com.pairproject.papercv.model.Word;

import java.util.List;
import java.util.Map;

/**
 * @author 李星源
 * @date 2021/03/25
 */
public interface DataService {

    List<Map.Entry<String, Integer>> getTop500();

    List<Word> getTrendWord(String meeting, List<String> years);
}
