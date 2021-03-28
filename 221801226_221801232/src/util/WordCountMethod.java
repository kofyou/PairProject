package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordCountMethod {
    //空白行的正则表达式
    private static String BLANK_LINE_REGEX = "^\\s*$";
    //非字母数字字符的正则表达式
    private static String UN_ALPHABET_NUM_REGEX = "[^0-9a-zA-Z]";
    //匹配以4个英文字母开头的正则表达式
    private static String FIRST_FOUR_APLH_REGEX = "^[a-z]{4,}.*";
    
    //记录文件内单词以及出现次数的TreeMap
    public static TreeMap<String, Integer> map = new TreeMap<>();
    
    /**
     * 统计文件的单词总数
     * @param str 文件转出的字符串
     * @return words 文件单词总数
     */
    public static int countWords(String str) {
        int words = 0;
        int num = 0;
        
        String lowerStr = str.toLowerCase();
        Pattern pat = Pattern.compile(UN_ALPHABET_NUM_REGEX);
        Matcher mat = pat.matcher(lowerStr);
        lowerStr = mat.replaceAll(" ");
        String[] word = lowerStr.split("\\s+");
        int len = word.length;
        String tw = null;
        for (int i = 0; i < len; i++) {
            tw = word[i];
            if (tw.matches(FIRST_FOUR_APLH_REGEX)) {
                words++;
                if (!map.containsKey(tw)) {
                    map.put(tw, 1);
                } 
                else {
                    num = map.get(tw);
                    map.put(tw, num + 1);
                }
            }
        }
        return words;
    }
    
    /**
     * 统计文件中各单词的出现次数
     * @param map 存储word以及出现次数的map键值对
     * @return list 存储出现频率最高的单词以及次数
     */
    
    public static List<Map.Entry<String, Integer>> highFreqWord(TreeMap<String, Integer> map){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> word1, Map.Entry<String, Integer> word2) {
                    return word2.getValue() - word1.getValue();
            }
        });
        if (list.size() >= 10) {
            return list.subList(0, 10);
        } else {
            return list.subList(0, list.size());
        }
    }
}

