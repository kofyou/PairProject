package cn.edu.fzu.papermanage.controller;

import cn.edu.fzu.papermanage.common.Result;
import cn.edu.fzu.papermanage.service.PaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    private PaperService paperService;

    /**
     * 模糊查询数据库中相关论文的所有简要信息
     *
     * @param content the content 要查询的内容
     * @return the papers by fuzzy search 相关论文的简要信息
     */
    @GetMapping("/search")
    public Result getPapersByFuzzySearch(@RequestParam String content) {
        return Result.success(paperService.fuzzyFindPaperByKeywordOrTitleOrAbstract(content));
    }

    /**
     * 模糊查询数据库中相关论文的所有信息，包括关键词，分页返回
     *
     * @param content  the content 要查询的内容
     * @param pageNum  the page num 页数
     * @param pageSize the page size 单页论文数
     * @return the paged full papers by fuzzy search 模糊搜索的已分页全部论文内容
     */
    @GetMapping("/searchPage")
    public Result getPagedFullPapersByFuzzySearch(@RequestParam String content
            ,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(paperService.fuzzyFindFullPaperByKeywordOrTitleOrAbstractAndPage(content,pageNum
                ,pageSize));
    }
}
