package cn.edu.fzu.papermanage.service;

import cn.edu.fzu.papermanage.dao.PaperDao;
import cn.edu.fzu.papermanage.dao.UserPaperDao;
import cn.edu.fzu.papermanage.entity.Paper;
import cn.edu.fzu.papermanage.entity.UserPaper;
import cn.edu.fzu.papermanage.util.SqlSentenceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserPaperService {
    @Resource
    private UserPaperDao userPaperDao;
    @Resource
    private PaperDao paperDao;

    public List<Paper> fuzzyAddUserPaperByTitle(Integer userId,String titleOrigin) {
        String titleProcessed = SqlSentenceUtil.splitAndAddFuzzy(titleOrigin);
        List<Paper> papers = paperDao.findPapersByTitle(titleProcessed);
        UserPaper userPaper = new UserPaper();
        userPaper.setUserId(userId);
        for (Paper paper : papers) {
            userPaper.setPaperId(paper.getId());
            userPaperDao.save(userPaper);
        }
        return papers;
    }
}
