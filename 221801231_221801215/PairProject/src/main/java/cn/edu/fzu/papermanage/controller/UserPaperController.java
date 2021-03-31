package cn.edu.fzu.papermanage.controller;

import cn.edu.fzu.papermanage.common.Result;
import cn.edu.fzu.papermanage.entity.Paper;
import cn.edu.fzu.papermanage.entity.PaperWithKeywords;
import cn.edu.fzu.papermanage.service.UserPaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/userPaper")
public class UserPaperController {
    @Resource
    private UserPaperService userPaperService;

    /**
     * 根据标题模糊查询，添加到用户论文列表
     *
     * @param id          the id session中用户id
     * @param titleOrigin the title origin 用户传入的未经处理的标题
     * @return the result
     */
    @GetMapping(path = "/add")
    public Result<List<Paper>> fuzzyAddUserPaperByTitle(@SessionAttribute Integer id,@RequestParam String titleOrigin) {
        List<Paper> papers = new ArrayList<>();
        Result<List<Paper>> result;
        result = new Result<>();
        result.setCode("-1");
        if (id != null) {
            if(titleOrigin!= null && !titleOrigin.isEmpty()) {
                papers = userPaperService.fuzzyAddUserPaperByTitle(id, titleOrigin);
            }else {
                result.setMsg("传入标题为空");
            }
        }else {
            result.setMsg("用户id为空");
        }
        if (papers.size()>0) {
            result = Result.success(papers);
        }else {
            result.setMsg("不存在匹配论文");
        }
        return result;
    }

    /**
     * 根据用户id（从session获取），获取其关联的所有论文内容（不包括关键词）
     *
     * @param id the id 用户id（从session获取）
     * @return the result 用户关联的所有论文信息列表用户id（不包括关键词）
     */
    @GetMapping("/all")
    public Result<List<Paper>> findAllPapersByUserId(@SessionAttribute Integer id) {
        return Result.success(userPaperService.findAllPapersByUserId(id));
    }


    /**
     * 根据用户id（Session中获取）和论文id（request参数）删除用户论文关联记录
     *
     * @param id      the id 用户id（Session中获取）
     * @param paperId the paper id 论文id（request参数）
     * @return the result 执行结果
     */
    @GetMapping("/delete")
    public Result deleteUserPaperByUserIdAndPaperId(@SessionAttribute Integer id,@RequestParam Integer paperId) {
        userPaperService.deleteUserPaperByUserIdAndPaperId(id,paperId);
        return Result.success();
    }

    /**
     * 根据关键词获取用户所有包含该关键词的论文
     *
     * @param id       the id 用户id
     * @param keyword  the keyword 关键词
     * @param pageNum  the page num 页数 默认为1
     * @param pageSize the page size 单页论文数 默认为5
     * @return the user full paper by keyword 所有包含传入关键词的论文（包括关键词）
     */
    @GetMapping("/keyword")
    public Result<List<PaperWithKeywords>> getUserFullPaperByKeyword(@SessionAttribute Integer id
            ,@RequestParam String keyword,@RequestParam(defaultValue = "1") Integer pageNum
            ,@RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(userPaperService.findUserFullPapersByKeyword(pageNum,pageSize,id,keyword));
    }

    /**
     * 获取用户关注论文中包含某个关键词的数量
     *
     * @param id      the id 用户id
     * @param keyword the keyword 查询的关键词
     * @return the result 包含关键词的论文数量
     */
    @GetMapping("/countByKeyword")
    public Result countByKeyword(@SessionAttribute Integer id,@RequestParam String keyword) {
        return Result.success(userPaperService.countUserPapersByKeyword(id,keyword));
    }

    /**
     * 获取用户所有关联论文的列表（包括关键词，可分页）
     *
     * @param id       the id 用户id
     * @param pageNum  the page num 页数
     * @param pageSize the page size 单页论文数
     * @return the all user paper by page 当前用户所有关联论文的列表（包括关键词，已分页）
     */
    @GetMapping("/contentsPage")
    public Result<List<PaperWithKeywords>> getAllUserPaperByPage(@SessionAttribute Integer id
            ,@RequestParam(defaultValue = "1") Integer pageNum
            ,@RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(userPaperService.findAllUserFullPaperByPage(pageNum,pageSize,id));
    }

    /**
     * 根据关键词，标题，摘要模糊查询用户关注论文的简要信息
     *
     * @param id            the id 用户id
     * @param originContent the origin content 查询的内容
     * @return the result 包含标题、论文id的论文列表
     */
    @GetMapping("/search")
    public Result fuzzyGetUserPaperByKeywordOrTitleOrAbstract(@SessionAttribute Integer id
            ,@RequestParam String originContent) {
        return Result.success(userPaperService.fuzzyGetUserPaperByKeywordOrTitleOrAbstract(id,originContent));
    }

    /**
     * 根据关键词，标题，摘要模糊查询用户关注论文的所有信息，并分页
     *
     * @param id            the id 用户id
     * @param originContent the origin content 查询内容
     * @param pageNum       the page num 查看页数
     * @param pageSize      the page size 单页论文数量
     * @return the result 分页后的相关论文完整信息列表
     */
    @GetMapping("/searchPage")
    public Result fuzzyGetFullUserPaperByKeywordOrTitleOrAbstractAndPage(@SessionAttribute Integer id
            ,@RequestParam String originContent,@RequestParam(defaultValue = "1") Integer pageNum
            ,@RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(userPaperService.fuzzySearchFullUserPaperAndPage(id,originContent,pageNum,pageSize));
    }
}
