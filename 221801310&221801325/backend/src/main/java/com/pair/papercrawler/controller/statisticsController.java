package com.pair.papercrawler.controller;

import com.pair.papercrawler.models.ResponseMessage;
import com.pair.papercrawler.service.StatisticsService;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*** 
* @Description: 统计图 返回数据
* @Param:  
* @return:  
* @Author: top
* @Date: 2021/3/30 
*/
@RestController
@RequestMapping("/statistics")
public class statisticsController {
    @Autowired
    StatisticsService statisticsService;
    /***
    * @Description: 返回热词 提取top10个热门领域或热门研究方向
    * @Param:  
    * @return:  
    * @Author: top
    * @Date: 2021/3/30
    */
    @GetMapping("/gettop20")
    public ResponseMessage gettop20() {
        return ResponseMessage.success(statisticsService.gettop20());
    }

}
