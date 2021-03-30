package cn.edu.fzu.papermanage.service;

import cn.edu.fzu.papermanage.dao.KeywordDao;
import cn.edu.fzu.papermanage.dao.PaperDao;
import cn.edu.fzu.papermanage.dao.UserPaperDao;
import cn.edu.fzu.papermanage.entity.Paper;
import cn.edu.fzu.papermanage.entity.PaperWithKeywords;
import cn.edu.fzu.papermanage.entity.UserPaper;
import cn.edu.fzu.papermanage.util.SqlSentenceUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserPaperService {
    @Resource
    private UserPaperDao userPaperDao;
    @Resource
    private PaperDao paperDao;
    @Resource
    private KeywordDao keywordDao;

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

    /**
     * 根据关键词获取用户所有包含该关键词的论文的列表（包含关键词，可分页）
     *
     * @param pageNum  the page num 页数
     * @param pageSize the page size 单页论文数量
     * @param userId   the user id 用户id
     * @param keyword  the keyword 关键字
     * @return the list 所有包含传入关键字的论文列表（包含关键词，已分页）
     */
    public List<PaperWithKeywords> findUserFullPapersByKeyword(Integer pageNum, Integer pageSize, Integer userId
            , String keyword) {
        Sort sort = new Sort(Sort.Direction.DESC,"publishYear");
        Pageable pageRequest = PageRequest.of(pageNum - 1,pageSize,sort);
        Page<Paper> papers = paperDao.findUserPapersByKeyword(userId,keyword,pageRequest);
        return getPaperWithKeywords(papers);
    }

    /**
     * 获取用户的所有关联论文（包括关键词），并分页
     *
     * @param pageNum  the page num 页数
     * @param pageSize the page size 单页论文数
     * @param userId   the user id 用户id
     * @return the list 分页后的用户关联论文列表
     */
    public List<PaperWithKeywords> findAllUserFullPaperByPage(Integer pageNum,Integer pageSize,Integer userId) {
        List<Paper> papers = paperDao.findAllUserPapersByPage(userId,(pageNum - 1) * pageSize,pageSize);
        return getPaperWithKeywords(papers);
    }

    private List<PaperWithKeywords> getPaperWithKeywords(Iterable<Paper> papers) {
        List<PaperWithKeywords> papersWithKeywords = new LinkedList<>();
        PaperWithKeywords paperWithKeywords;
        for (Paper paper : papers) {
            paperWithKeywords = new PaperWithKeywords(paper);
            paperWithKeywords.setKeywords(keywordDao.findKeywordsByPaperId(paper.getId()));
            papersWithKeywords.add(paperWithKeywords);
        }
        return papersWithKeywords;
    }
}
