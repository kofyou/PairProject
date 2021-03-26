package com.pairproject.papercv.service.impl;

import com.pairproject.papercv.dao.PaperMapper;
import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.model.Word;
import com.pairproject.papercv.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 李星源
 * @date 2021/03/25
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public List<Map.Entry<String, Integer>> getTop500() {
        List<Paper> papers = paperMapper.selectAll();
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
        Collections.sort(words, (o1, o2) -> o2.getValue() - o1.getValue());
        return words.subList(0, 500);
    }

    @Override
    public List<Word> getTrendWord(String meeting, List<String> years) {
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
        for (Paper paper : papers) {
            for (String year : years) {
                if (year.equals(paper.getYear())) {
                    for (String key : paper.getKeyWord().split(",")) {
                        for (int i = 0;i < 5;i++){
                            if (words.get(i).getKey().equals(key)) {
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

        List<Word> res = new ArrayList<>(8);
        for (int i = 0;i < 5;i++){
            res.add(new Word(words.get(i).getKey(), years, new ArrayList<>()));
        }

        for (String year : years) {
            for (int j = 0; j < 5; j++) {
                String keyMap = year + "_" + words.get(j).getKey();
                res.get(j).getCounts().add(hotWord.getOrDefault(keyMap, 0));
            }
        }

        return res;
    }

    @Override
    public int getPaperCount(String meeting) {
        return paperMapper.readCount(meeting);
    }

}
