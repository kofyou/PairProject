package com.pair.papercrawler.controller;

import com.pair.papercrawler.models.ResponseMessage;
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
    /*** 
    * @Description: 返回热词 提取top10个热门领域或热门研究方向
    * @Param:  
    * @return:  
    * @Author: top
    * @Date: 2021/3/30
    */
    @GetMapping("/gettop10")
    public ResponseMessage gettop10() {

        return ResponseMessage.success(null);
    }

}
