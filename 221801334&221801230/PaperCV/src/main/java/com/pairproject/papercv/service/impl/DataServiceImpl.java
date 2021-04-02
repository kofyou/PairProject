package com.pairproject.papercv.service.impl;

import com.pairproject.papercv.dao.PaperMapper;
import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.model.Word;
import com.pairproject.papercv.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataService实现类
 *
 * @date 2021/03/25
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private PaperMapper paperMapper;

    /**
     * 获取top500热词
     *
     * @return
     */
    @Override
    public List<Map.Entry<String, Integer>> getTop500() {
        List<Paper> papers = paperMapper.selectAll();
        Map<String, Integer> wordMap = new HashMap<>();
        // 存储关键词个数
        for (Paper paper : papers){
            for (String word : paper.getKeyWord().split(",")){
                if (word.length() < 3) {// 过滤长度小于3的关键词
                    continue;
                }
                if (wordMap.containsKey(word)){
                    wordMap.put(word, wordMap.get(word) + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordMap.entrySet());
        words.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return words.subList(0, 500);
    }

    /**
     * 获取热词趋势
     *
     * @param meeting
     * @param years
     * @return
     */
    @Override
    public List<Word> getTrendWord(String meeting, List<String> years) {
        // 获取该会议热词的排序
        List<Paper> papers = paperMapper.selectByMeeting(meeting);
        Map<String, Integer> wordMap = new HashMap<>();
        for (Paper paper : papers){
            for (String word : paper.getKeyWord().split(",")){
                if (word.length() < 3) {
                    continue;
                }
                if (wordMap.containsKey(word)){
                    wordMap.put(word, wordMap.get(word) + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordMap.entrySet());
        words.sort((o1, o2) -> o2.getValue() - o1.getValue());

        Map<String, Integer> hotWord = new HashMap<>();
        for (Paper paper : papers) { // 遍历所有论文
            for (String year : years) { // 遍历所有年限
                if (year.equals(paper.getYear())) {// 年份匹配
                    for (String key : paper.getKeyWord().split(",")) {
                        for (int i = 0;i < 5;i++){
                            if (words.get(i).getKey().equals(key)) {// 关键词匹配
                                // 个数+1
                                String keyMap = year + "_" + key;
                                if (hotWord.containsKey(keyMap)) {
                                    hotWord.put(keyMap, hotWord.get(keyMap) + 1);
                                } else {
                                    hotWord.put(keyMap, 1);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

        // 初始化结果列表
        List<Word> res = new ArrayList<>(8);
        for (int i = 0;i < 5;i++){
            res.add(new Word(words.get(i).getKey(), years, new ArrayList<>()));
        }

        // 填入每个年份每个关键词的个数
        for (String year : years) {
            for (int j = 0; j < 5; j++) {
                String keyMap = year + "_" + words.get(j).getKey();
                res.get(j).getCounts().add(hotWord.getOrDefault(keyMap, 0));
            }
        }

        return res;
    }

    /**
     * 获取论文数量
     *
     * @param meeting
     * @return
     */
    @Override
    public int getPaperCount(String meeting) {
        return paperMapper.readCount(meeting);
    }

}
