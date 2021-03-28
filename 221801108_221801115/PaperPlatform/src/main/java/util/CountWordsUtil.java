package util;

import pojo.Keyword;

import java.util.*;

public class CountWordsUtil {

    /* 统计词频率并返回一个存储对应数据的list */
    public static List<Map.Entry<String, Integer>> countWordFrequency(String words)
    {
        Map<String, Integer> map = new HashMap<>();
        List<Map.Entry<String, Integer>> list;

        String[] ws = words.split(",");

        for (String word:ws)
        {
            if (map.containsKey(word))
            {
                map.put(word, map.get(word) + 1);
            }
            else
            {
                map.put(word, 1);
            }
        }

        //通过ArrayList构造函数把map.entrySet()转换成list
        list = new ArrayList<>(map.entrySet());
        return list;
    }

    /* 让词按照频率优先，字典序其次的顺序排序 */
    public static List<Map.Entry<String, Integer>> sortWords(List<Map.Entry<String, Integer>> list)
    {
        list.sort((a, b) -> {
            if (a.getValue().equals(b.getValue()))
            {
                return a.getKey().compareTo(b.getKey());
            }
            else
            {
                return b.getValue().compareTo(a.getValue());
            }
        });

        return list;
    }

    /* 用迭代器得到一个存储十个单词及其频率的字符串并返回 */
    public static ArrayList<Keyword> getSortedWordsAndFrequency(String words) {
        ArrayList<Keyword> kwList = new ArrayList<>();
        List<Map.Entry<String, Integer>> list = sortWords(countWordFrequency(words));
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        Map.Entry<String, Integer> entry;

        for (int i = 0; iterator.hasNext() && i < 10 ; i++)
        {
            entry = iterator.next();
            Keyword kw = new Keyword();
            kw.setName(entry.getKey());
            kw.setFrequency(entry.getValue());
            kwList.add(kw);
        }

        return kwList;
    }
}
