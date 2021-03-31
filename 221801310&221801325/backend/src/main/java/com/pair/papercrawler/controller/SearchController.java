package com.pair.papercrawler.controller;


import com.github.pagehelper.PageInfo;
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
    @PostMapping("/searchPaper")
    public ResponseMessage searchPaper(@RequestBody RequestMessage requestMessage) {
        String type = requestMessage.getSearchType();
        String content = requestMessage.getContent();
        Integer pageNum = requestMessage.getPageNum();
        Integer pageSize = requestMessage.getPageSize();

        PageInfo<Paper> pageInfo = new PageInfo<>();
        if (type.equals("author")) {
            pageInfo = authorService.getPaperByAuthorName(content,pageNum,pageSize);
        } else if (type.equals("keyword")) {
            pageInfo = keywordsService.getPaperByKeyword(content,pageNum,pageSize);
        } else if (type.equals("title")) {
            pageInfo = papersService.selectPaperByTitle(content,pageNum,pageSize);
        } else if (type.equals("abstracts")) {
            pageInfo = papersService.selectPaperByAbstracts(content,pageNum,pageSize);
        } else {
            return ResponseMessage.failure("请传入正确的搜索类型");
        }
        return ResponseMessage.success(pageInfo);
    }


    /*** 
     * @Description: 返回所有论文
     * @Param:
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    @GetMapping("/getAllPaper")
    public ResponseMessage getAllPaper(@RequestParam(name="pageNum") Integer pageNum,@RequestParam(name = "pageSize") Integer pageSize) {
        return ResponseMessage.success(papersService.selectAll(pageNum,pageSize));
    }

    /***
     * @Description: 获取某个论坛的所有论文
     * @Param: CVPR ICCV  ECCV
     * @return:
     * @Author: top
     * @Date: 2021/3/30
     */
    @PostMapping("/get_Magazine")
    public ResponseMessage getMagazine(@RequestBody RequestMessage requestMessage) {
        String content = requestMessage.getContent();
        Integer pageNum = requestMessage.getPageNum();
        Integer pageSize = requestMessage.getPageSize();
        return ResponseMessage.success(papersService.selectPaperByMagazine(content,pageNum,pageSize));
    }

    @DeleteMapping("/deletePaper")
    public ResponseMessage deletePaper(@RequestParam(name = "paperId") Integer paperId){
        papersService.deletePaperByPaperId(paperId);
        return ResponseMessage.success("successfully delete");
    }
}
