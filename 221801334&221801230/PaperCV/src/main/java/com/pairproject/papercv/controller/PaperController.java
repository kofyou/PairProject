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
        return paperService.getAll();
    }

    /**
     * 根据论文题目获取论文
     *
     * @param title
     * @return
     */
    @GetMapping("/{title}")
    public Paper getPaper(@PathVariable("title") String title) {
        return paperService.getPaper(title);
    }

    @GetMapping("/keyWord/{key}")
    public List<Paper> getPaperByKey(@PathVariable("key") String key) {
        return paperService.getPaperByKey(key);
    }
}
