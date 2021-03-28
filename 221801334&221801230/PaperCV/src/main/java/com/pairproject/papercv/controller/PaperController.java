package com.pairproject.papercv.controller;

import com.pairproject.papercv.model.Paper;
import com.pairproject.papercv.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 获取所有的论文
     *
     * @return 论文列表
     */
    @GetMapping("/all")
    public List<Paper> getPaperList() {
        return paperService.getAll().subList(0,1000);
    }

    /**
     * 根据论文标题查询论文（模糊查询）
     *
     * @param title 论文标题
     * @return 论文列表
     */
    @GetMapping("/{title}")
    public List<Paper> getPaper(@PathVariable("title") String title) {
        return paperService.getPaper(title);
    }

    /**
     * 通过关键词获取论文
     *
     * @param key
     * @return
     */
    @GetMapping("/keyWord/{key}")
    public List<Paper> getPaperByKey(@PathVariable("key") String key) {
        return paperService.getPaperByKey(key);
    }
}
