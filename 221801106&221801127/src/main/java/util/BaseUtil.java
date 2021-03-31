package util;

import baseclass.Paper;

import java.util.*;

public class BaseUtil {
    public static void merge(HashMap<String,Integer> hashMap1, HashMap<String,Integer> hashMap2){  //合并两个hashmap<String,Integer>
        String key;
        Integer value;
        for (Map.Entry<String,Integer> hashmap : hashMap2.entrySet()){
            key = hashmap.getKey();
            value = hashmap.getValue();
            if (hashMap1.containsKey(key)){
                hashMap1.put(key,value + hashMap1.get(key));
            }
            else{
                hashMap1.put(key,value);
            }
        }
    }

    public static HashMap<String,Integer> parseHashmap(String[] temp){  //将Paper里面的热词字符串数组转化为hashmap
        HashMap<String,Integer> hotwords = new HashMap<>();
        for (String hotword : temp){
            hotwords.put(hotword,1);
        }
        return hotwords;
    }

    public static List<Map.Entry<String, Integer>> sortHashMap(HashMap<String,Integer> hashMap){
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    public static void showHashMap(HashMap<String,Integer> hotwords){
        for (Map.Entry<String,Integer> temp : hotwords.entrySet()){
            System.out.println(temp.getKey()+":"+temp.getValue());
        }
    }

    public static void showList(List<Map.Entry<String, Integer>> list){
        for (Map.Entry<String,Integer> temp : list){
            System.out.println(temp.getKey()+":"+temp.getValue());
        }
    }

    public static void showPapers(List<Paper> list){
        for (Paper paper : list){
            System.out.println(paper);
        }
    }
}
