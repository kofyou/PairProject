package com.pairproject.papercv.controller;

import com.pairproject.papercv.model.Word;
import com.pairproject.papercv.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 数据统计控制器
 *
 * @date 2021/03/25
 */
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/trend/iccv")
    public List<Word> getTrendICCV() {
        return dataService.getTrendWord("ICCV", Arrays.asList("2011", "2013", "2015", "2017", "2019"));
    }

    @GetMapping("/trend/eccv")
    public List<Word> getTrendECCV() {
        return dataService.getTrendWord("ECCV", Arrays.asList("2016", "2018", "2020"));
    }

    @GetMapping("/trend/cvpr")
    public List<Word> getTrendCVPR() {
        return dataService.getTrendWord("CVPR", Arrays.asList("2016", "2017", "2018", "2019", "2020"));
    }

    @GetMapping("/meeting/count")
    public List<Integer> getPaperCount() {
        List<Integer> counts = new ArrayList<>();
        counts.add(dataService.getPaperCount("CVPR"));
        counts.add(dataService.getPaperCount("ICCV"));
        counts.add(dataService.getPaperCount("ECCV"));
        return counts;
    }

    @GetMapping("/hotWord")
    public List<Map.Entry<String, Integer>> getHotWord() {
        return dataService.getTop500();
    }

}
