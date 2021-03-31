package cn.edu.fzu.papermanage.controller;

import cn.edu.fzu.papermanage.common.Result;
import cn.edu.fzu.papermanage.service.KeywordWithFrequencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/keywordWithFrequency")
public class KeywordWithFrequencyController {
    @Resource
    private KeywordWithFrequencyService keywordWithFrequencyService;

    /**
     * 获取数据库中出现频数最高的十个关键词，及出现年份，对应年份出现次数，所属会议
     *
     * @return the all keyword frequency
     */
    @GetMapping("/all")
    public Result getAllKeywordFrequency() {
        return Result.success(keywordWithFrequencyService.findAllKeywordFrequency());
    }

    /**
     * 根据顶会名称获取数据库中对应顶会出现频数最高的十个关键词，及出现年份，对应年份出现次数，所属会议
     *
     * @param source the source
     * @return the keyword frequency by source
     */
    @GetMapping("/source")
    public Result getKeywordFrequencyBySource(@RequestParam String source) {
        return Result.success(keywordWithFrequencyService.findKeywordsFrequencyBySource(source));
    }
}
