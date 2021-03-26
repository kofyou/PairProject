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

    /**
     * 通过标题模糊搜索论文，并关联到用户
     *
     * @param userId      the user id 用户id
     * @param titleOrigin the title origin 传入的未经处理的标题
     * @return the list 包含所有成功添加的论文信息的列表
     */
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

    /**
     * 查找用户关联的所有论文信息（不包括关键词）
     *
     * @param userId the user id 用户id
     * @return the list 包含所有用户关联的论文信息（不包括关键词）
     */
    public List<Paper> findAllPapersByUserId(Integer userId) {
        return paperDao.findAllPapersByUserId(userId);
    }

    /**
     * 根据用户id和论文id从用户关联的论文表中删除关联的记录
     *
     * @param userId  the user id 需要删除的用户id
     * @param paperId the paper id 要删除的论文id
     */
    public void deleteUserPaperByUserIdAndPaperId(Integer userId,Integer paperId) {
        userPaperDao.deleteUserPaperByUserIdAndPaperId(userId,paperId);
    }
}
