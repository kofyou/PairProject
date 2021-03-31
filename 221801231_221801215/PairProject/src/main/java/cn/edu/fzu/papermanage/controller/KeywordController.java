package cn.edu.fzu.papermanage.controller;

import cn.edu.fzu.papermanage.common.Result;
import cn.edu.fzu.papermanage.entity.Keyword;
import cn.edu.fzu.papermanage.service.KeywordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/keyword")
public class KeywordController {
    @Resource
    private KeywordService keywordService;

    /**
     * 获取用户关联的前十大热词
     *
     * @param id the id
     * @return the ten keyword result.data:热度由高到低排列的关键词列表
     */
    @GetMapping("/userTopTen")
    public Result<List<String>> getTenKeywordByUserId(@SessionAttribute Integer id) {
        return Result.success(keywordService.getUsersTopTenKeywordByUserId(id));
    }
}
