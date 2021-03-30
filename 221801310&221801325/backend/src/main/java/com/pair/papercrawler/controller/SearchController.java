package com.pair.papercrawler.controller;


import com.pair.papercrawler.models.Paper;
import com.pair.papercrawler.models.RequestMessage;
import com.pair.papercrawler.models.ResponseMessage;
import com.pair.papercrawler.service.AuthorService;
import com.pair.papercrawler.service.KeywordsService;
import com.pair.papercrawler.service.PapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private KeywordsService keywordsService;
    @Autowired
    private PapersService papersService;


    /*** 
     * @Description: 搜索论文 可以通过 title keyword author abstracts 查询
     * @Param:
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    @GetMapping("/searchPaper")
    public ResponseMessage searchPaper(@RequestBody RequestMessage requestMessage) {
        String type = requestMessage.getSearchType();
        String content = requestMessage.getContent();
        List<Paper> paperList = new ArrayList<Paper>();
        if (type.equals("author")) {
            paperList = authorService.getPaperByAuthorName(content);
        } else if (type.equals("keyword")) {
            paperList = keywordsService.getPaperByKeyword(content);
        } else if (type.equals("title")) {
            paperList = papersService.selectPaperByTitle(content);
        } else if (type.equals("abstracts")) {
            paperList = papersService.selectPaperByAbstracts(content);
        } else {
            return ResponseMessage.failure("请传入正确的搜索类型");
        }
        return ResponseMessage.success(paperList);
    }


    /*** 
     * @Description: 返回所有论文
     * @Param:
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    @GetMapping("/getAllPaper")
    public ResponseMessage getAllPaper() {
        return ResponseMessage.success(papersService.selectAll());
    }

    /***
     * @Description: 获取某个论坛的所有论文
     * @Param: CVPR ICCV  ECCV
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    @GetMapping("/get_Magazine")
    public ResponseMessage getMagazine(@RequestParam(name = "magazine") String magazine) {
        return ResponseMessage.success(papersService.selectPaperByMagazine(magazine));
    }
}
