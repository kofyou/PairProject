package cn.edu.fzu.papermanage.service;

import cn.edu.fzu.papermanage.dao.KeywordDao;
import cn.edu.fzu.papermanage.dao.PaperDao;
import cn.edu.fzu.papermanage.entity.Paper;
import org.springframework.data.domain.Page;
import cn.edu.fzu.papermanage.entity.PaperWithKeywords;
import cn.edu.fzu.papermanage.util.SqlSentenceUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaperService {
    @Resource
    private PaperDao paperDao;

    @Resource
    private KeywordDao keywordDao;

    /**
     * 通过关键词，标题，摘要模糊查询数据库中相关的论文，仅包括论文id，标题，来源，发布年份（不包括关键词，摘要，原文链接，返回全部）
     *
     * @param originContent the origin content 未经修改的查询内容
     * @return the list 相关论文，仅包括论文id，标题，来源，发布年份（不包括关键词，摘要，原文链接，返回全部）
     */
    public List<Paper> fuzzyFindPaperByKeywordOrTitleOrAbstract(String originContent) {
        String fuzzyContent = SqlSentenceUtil.splitAndAddFuzzy(originContent);
        List<Paper> papers = paperDao.fuzzyFindPaperByKeywordOrTitleOrAbstract(fuzzyContent);
        for(Paper paper : papers) {
            paper.set_abstract("");
            paper.setUrl("");
        }
        return papers;
    }

    /**
     * 通过关键词，标题，摘要模糊查询数据库中相关的论文（包括关键词，及所有相关论文信息，分页返回）
     *
     * @param originContent the origin content 未经修改的查询内容
     * @param pageNum       the page num 要获取的页数
     * @param pageSize      the page size 单页论文数量
     * @return the list 相关的论文（包括关键词，及所有相关论文信息，分页返回）
     */
    public List<PaperWithKeywords> fuzzyFindFullPaperByKeywordOrTitleOrAbstractAndPage(String originContent
             ,Integer pageNum,Integer pageSize) {
        String fuzzyContent = SqlSentenceUtil.splitAndAddFuzzy(originContent);
        List<Paper> paperPage = paperDao.fuzzyFindPaperByKeywordOrTitleOrAbstractAndPage(fuzzyContent
                ,(pageNum - 1) * pageSize,pageSize);
        List<PaperWithKeywords> papersWithKeywords = new ArrayList<>();
        PaperWithKeywords paperWithKeywords;
        for (Paper paper : paperPage) {
            paperWithKeywords = new PaperWithKeywords(paper);
            paperWithKeywords.setKeywords(keywordDao.findKeywordsByPaperId(paper.getId()));
            papersWithKeywords.add(paperWithKeywords);
        }
        return papersWithKeywords;
     }
}
