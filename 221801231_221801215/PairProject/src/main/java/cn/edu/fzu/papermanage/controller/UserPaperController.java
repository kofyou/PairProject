package cn.edu.fzu.papermanage.controller;

import cn.edu.fzu.papermanage.common.Result;
import cn.edu.fzu.papermanage.dao.PaperDao;
import cn.edu.fzu.papermanage.entity.Paper;
import cn.edu.fzu.papermanage.service.UserPaperService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result<List<Paper>> fuzzyAddUserPaperByTitle(@SessionAttribute Integer id, @RequestParam String titleOrigin) {
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
}
