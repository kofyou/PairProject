package com.pairproject.papercv.controller;

import com.alibaba.fastjson.JSONObject;
import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 论文控制器
 *
 * @date 2021/03/23
 */
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    private static List<Paper> allPaper = null;
    private static Map<String, List<Paper>> keyWordList = new HashMap<>(64);
    private static Map<String, List<Paper>> titleList = new HashMap<>(2048);

    /**
     * 获取所有论文
     *
     * @param page
     * @return
     */
    @GetMapping("/all/{page}")
    public JSONObject getPaperList(@PathVariable("page") int page) {
        int start = (page -1) * 10;
        JSONObject jsonObject = new JSONObject();
        if (allPaper != null) {
            int end = Math.min(start + 10, allPaper.size());
            jsonObject.put("count", allPaper.size());
            jsonObject.put("data", allPaper.subList(start, end));
            return jsonObject;
        }
        allPaper = paperService.getAll();
        int end = Math.min(start + 10, allPaper.size());
        jsonObject.put("count", allPaper.size());
        jsonObject.put("data", allPaper.subList(start, end));
        return jsonObject;
    }

    /**
     * 根据论文标题查询论文（模糊查询）
     *
     * @param title
     * @param page
     * @param isSort
     * @return
     */
    @GetMapping("/{title}/{page}")
    public JSONObject getPaper(@PathVariable("title") String title, @PathVariable("page") int page, Integer isSort) {
        int start = (page -1) * 10;
        JSONObject jsonObject = new JSONObject();
        List<Paper> paperList;
        if (titleList.containsKey(title) && (titleList.get(title) != null)) {
            paperList = titleList.get(title);
            if (isSort != 0) {
                paperList.sort(Comparator.comparing(Paper::getTitle));
            }
            int end = Math.min(start + 10, paperList.size());
            jsonObject.put("count", paperList.size());
            jsonObject.put("data", paperList.subList(start, end));
            return jsonObject;
        }
        paperList = paperService.getPaper(title);
        titleList.put(title, paperList);
        if (isSort != 0) {
            paperList.sort(Comparator.comparing(Paper::getTitle));
        }
        int end = Math.min(start + 10, paperList.size());
        jsonObject.put("count", paperList.size());
        jsonObject.put("data", paperList.subList(start, end));
        return jsonObject;
    }

    /**
     * 通过关键词查询论文
     *
     * @param key
     * @param page
     * @return
     */
    @GetMapping("/keyWord/{key}/{page}")
    public JSONObject getPaperByKey(@PathVariable("key") String key, @PathVariable("page") int page) {
        int start = (page -1) * 10;
        JSONObject jsonObject = new JSONObject();
        List<Paper> paperList;
        if (keyWordList.containsKey(key)) {
            paperList = keyWordList.get(key);
            int end = Math.min(start + 10, paperList.size());
            jsonObject.put("count", paperList.size());
            jsonObject.put("data", paperList.subList(start, end));
            return jsonObject;
        }
        paperList = paperService.getPaperByKey(key);
        keyWordList.put(key, paperList);
        int end = Math.min(start + 10, paperList.size());
        jsonObject.put("count", paperList.size());
        jsonObject.put("data", paperList.subList(start, end));
        return jsonObject;
    }
}
