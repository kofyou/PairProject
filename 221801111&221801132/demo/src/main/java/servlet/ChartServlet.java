package servlet;

import dao.WordDAO;
import dao.WordDAOImpl;
import pojo.HotWord;
import pojo.KeyWord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet("/ChartServlet")
public class ChartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        WordDAO wordDAO = new WordDAOImpl();
        List<KeyWord> keyWordList = new ArrayList<>();
        keyWordList = wordDAO.listGetByYear2012CVPR();//关键词列表
        Map<String,Integer> map = new HashMap<String,Integer>();//创建map,key保存字符串,value保存出现的次数
        for (KeyWord word : keyWordList) {
            String s = word.getWord();
            String[] temp = strCut(s);
            int size = temp.length;
            for (int i = 0; i < size; i++) {//遍历temp数组
                if (map.containsKey(temp[i])) {
                    map.put(temp[i], map.get(temp[i]) + 1);
                }
                else
                    map.put(temp[i],1);
            }
        }
        map = sortByValueDescending(map);
        List<HotWord> CVPRHotWordList = new ArrayList<>();
        for (Map.Entry<String,Integer> vo : map.entrySet()) {
            HotWord hotWord = new HotWord();
            hotWord.setWord(vo.getKey());
            hotWord.setNum(vo.getValue());
            hotWord.setType("CVPR");
            CVPRHotWordList.add(hotWord);
        }

        HotWord hotWord = new HotWord();
        hotWord = CVPRHotWordList.get(0);
        System.out.println(hotWord.getWord() + " " + hotWord.getNum() + " " + hotWord.getType());
*/



        List<HotWord> CVPRHotWordList = new ArrayList<>();
        for (int year = 2000;year < 2021;year++) {
            WordDAO wordDAO = new WordDAOImpl();
            List<KeyWord> keyWordList = new ArrayList<>();
            keyWordList = wordDAO.listGetByYearCVPR(Integer.toString(year));//关键词列表
            //if (keyWordList == null) System.out.println("空");
            if (keyWordList != null) {
                Map<String,Integer> map = new HashMap<String,Integer>();//创建map,key保存字符串,value保存出现的次数
                for (KeyWord word : keyWordList) {
                    String s = word.getWord();
                    String[] temp = strCut(s);
                    int size = temp.length;
                    for (int i = 0; i < size; i++) {//遍历temp数组
                        if (map.containsKey(temp[i])) {
                            map.put(temp[i], map.get(temp[i]) + 1);
                        }
                        else
                            map.put(temp[i],1);
                    }
                }
                map = sortByValueDescending(map);
                List<HotWord> HotWordList = new ArrayList<>();
                for (Map.Entry<String,Integer> vo : map.entrySet()) {
                    HotWord hotWord = new HotWord();
                    hotWord.setWord(vo.getKey());
                    hotWord.setNum(vo.getValue());
                    hotWord.setYear(Integer.toString(year));
                    hotWord.setType("CVPR");
                    HotWordList.add(hotWord);
                }

                //System.out.println(HotWordList.get(0).getWord());

                /*HotWord[] hotWord1 = new HotWord[10];
                for (int i = 0;i < 10;i++) {
                    hotWord1[i] = HotWordList.get(i);
                }

                for (int i = 0;i < 10;i++) {
                    System.out.println(hotWord1[i].getWord() + " " + hotWord1[i].getNum() + " " + hotWord1[i].getType() + " " +
                            hotWord1[i].getYear());
                }*/
                //System.out.println(year);

                HotWord hotWord = new HotWord();
                hotWord = HotWordList.get(0);
                CVPRHotWordList.add(hotWord);//CVPR每年的热词
                /*System.out.println(hotWord.getWord() + " " + hotWord.getNum() + " " + hotWord.getType() + " " +
                        hotWord.getYear());*/
            }
        }

/*
        List<HotWord> ICCVHotWordList = new ArrayList<>();
        for (int year = 2001;year < 2021;year++) {
            WordDAO wordDAO = new WordDAOImpl();
            List<KeyWord> keyWordList1 = new ArrayList<>();
            keyWordList1 = wordDAO.listGetByYearICCV(Integer.toString(year));//关键词列表

            for (KeyWord keyWord : keyWordList1) {
                System.out.println(keyWord.getWord() + " " + keyWord.getYear());
            }

            Map<String,Integer> map = new HashMap<String,Integer>();//创建map,key保存字符串,value保存出现的次数
            for (KeyWord word : keyWordList1) {
                String s = word.getWord();
                String[] temp = strCut(s);
                int size = temp.length;
                for (int i = 0; i < size; i++) {//遍历temp数组
                    if (map.containsKey(temp[i])) {
                        map.put(temp[i], map.get(temp[i]) + 1);
                    }
                    else
                        map.put(temp[i],1);
                }
            }
            map = sortByValueDescending(map);

            for (Map.Entry<String,Integer> vo : map.entrySet()) {
                System.out.println(vo.getKey() + " " + vo.getValue());
            }

            List<HotWord> HotWordList1 = new ArrayList<>();
            for (Map.Entry<String,Integer> vo : map.entrySet()) {
                HotWord hotWord1 = new HotWord();
                hotWord1.setWord(vo.getKey());
                hotWord1.setNum(vo.getValue());
                hotWord1.setYear(Integer.toString(year));
                hotWord1.setType("ICCV");
                HotWordList1.add(hotWord1);
            }

            //System.out.println(HotWordList.get(0).getWord());

            HotWord[] hotWord1 = new HotWord[10];
            for (int i = 0;i < 10;i++) {
                hotWord1[i] = HotWordList1.get(i);
            }

            for (int i = 0;i < 10;i++) {
                System.out.println(hotWord1[i].getWord() + " " + hotWord1[i].getNum() + " " + hotWord1[i].getType() + " " +
                        hotWord1[i].getYear());
            }
            System.out.println();

            //HotWord hotWord = new HotWord();
            //hotWord = HotWordList.get(0);
            //CVPRHotWordList.add(hotWord);
            //System.out.println(hotWord.getWord() + " " + hotWord.getNum() + " " + hotWord.getType());
        }
*/

/*
        List<HotWord> ECCVHotWordList = new ArrayList<>();
        for (int year = 2016;year < 2021;year++) {
            WordDAO wordDAO = new WordDAOImpl();
            List<KeyWord> keyWordList = new ArrayList<>();
            keyWordList = wordDAO.listGetByYearECCV(Integer.toString(year));//关键词列表

            if (keyWordList == null) {
                System.out.println("空");
                List<HotWord> HotWordList = new ArrayList<>();
                HotWord hotWord = new HotWord();
                hotWord.setWord("");
                hotWord.setNum(0);
                hotWord.setType("ECCV");
                HotWordList.add(hotWord);

                HotWord hotWord1 = new HotWord();
                hotWord1 = HotWordList.get(0);
                ECCVHotWordList.add(hotWord1);
                System.out.println(hotWord.getWord() + " " + hotWord.getNum() + " " + hotWord.getType());
            }

            if (keyWordList != null) {
                Map<String,Integer> map = new HashMap<String,Integer>();//创建map,key保存字符串,value保存出现的次数
                for (KeyWord word : keyWordList) {
                    String s = word.getWord();
                    String[] temp = strCut(s);
                    int size = temp.length;
                    for (int i = 0; i < size; i++) {//遍历temp数组
                        if (map.containsKey(temp[i])) {
                            map.put(temp[i], map.get(temp[i]) + 1);
                        }
                        else
                            map.put(temp[i],1);
                    }
                }
                map = sortByValueDescending(map);

                System.out.println("KWZ");
                List<HotWord> HotWordList = new ArrayList<>();
                for (Map.Entry<String,Integer> vo : map.entrySet()) {
                    HotWord hotWord = new HotWord();
                    hotWord.setWord(vo.getKey());
                    hotWord.setNum(vo.getValue());
                    hotWord.setType("ECCV");
                    HotWordList.add(hotWord);
                }


                System.out.println(HotWordList.get(0).getWord());

                HotWord hotWord1 = new HotWord();
                hotWord1 = HotWordList.get(0);
                ECCVHotWordList.add(hotWord1);
                System.out.println(hotWord1.getWord() + " " + hotWord1.getNum() + " " + hotWord1.getType());
            }

        }

*/

        request.setAttribute("list", CVPRHotWordList);
        request.getRequestDispatcher("CVPR.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static boolean isWord(String str) {//判断仅包含 字母\数字\空格\- 的字符串
        String regex = "^[a-z0-9A-Z -]+$";
        return str.matches(regex);
    }

    public static String[] strCut(String s) {//字符串切割
        String[] tempStr = new String[100];//仅包含字母和数字的字符串数组
        int cnt = 0;//字符串个数计数
        Pattern pattern = Pattern.compile(",");
        String[] newStr = pattern.split(s);
        for(String ss : newStr) {
            if (isWord(ss)) {
                tempStr[cnt++] = ss;
            }
        }
        String[] Str = new String[cnt];
        for (int i = 0;i < cnt;i++) {
            Str[i] = tempStr[i];
        }
        return Str;
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = o1.getValue().compareTo(o2.getValue());
                return -compare;
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}