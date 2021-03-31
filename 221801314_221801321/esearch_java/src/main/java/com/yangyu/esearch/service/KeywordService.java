package com.yangyu.esearch.service;

import com.yangyu.esearch.entity.Keyword;
import com.yangyu.esearch.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.service.KeywordService
 * @Description : 关键词的业务处理
 * @date : 2021-03-27 19:47:39
 * Copyright  2021 user. All rights reserved.
 */
@Service
public class KeywordService {


    private Map<String,Integer> wordsMap;


    //返回词频最高的十个关键词
    public List<Keyword> topWord(List<Paper> papers)
    {
        wordsMap = new HashMap<>();
        Map<String,Integer> result = new LinkedHashMap<>();
        List<Keyword> keywordList = new LinkedList<>();
        for(Paper paper : papers)
        {
            for (String keyword : paper.getKeyword().split(","))
            {
                if (keyword.length() < 3)
                {
                    continue;
                }
                if (wordsMap.containsKey(keyword))
                {
                    wordsMap.put(keyword, wordsMap.get(keyword) + 1);
                }
                else
                {
                    wordsMap.put(keyword, 1);
                }

            }
        }
        wordsMap.entrySet().stream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed()).limit(10)
                .forEachOrdered(x->result.put(x.getKey(),x.getValue()));

        Iterator<Map.Entry<String, Integer>> it = result.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Integer> entry = it.next();
            Keyword keyWord = new Keyword();
            keyWord.setKeyword(entry.getKey());
            keyWord.setNum(entry.getValue());
            keywordList.add(keyWord);
        }
        return keywordList;
    }
}
