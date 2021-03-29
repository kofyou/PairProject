package cn.edu.fzu.papermanage.service;

import cn.edu.fzu.papermanage.dao.KeywordDao;
import cn.edu.fzu.papermanage.dao.KeywordWithFrequencyDao;
import cn.edu.fzu.papermanage.entity.KeywordWithFrequency;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class KeywordWithFrequencyService {
    @Resource
    private KeywordWithFrequencyDao keywordWithFrequencyDao;
    @Resource
    private KeywordDao keywordDao;

    /**
     * 获取数据库中出现频数最高的十个关键词，出现年份及其各年出现频数
     *
     * @return the list 出现频率最高的十个关键词，及年份，频数，来源
     */
    public List<List<KeywordWithFrequency> > findAllKeywordFrequency() {
        List<String> topTenKeywords = keywordDao.findTopTenFrequencyKeyword();
        List<List<KeywordWithFrequency> > keywordsWithFrequency = new LinkedList<>();
        for (String topTenKeyword : topTenKeywords) {
            List<KeywordWithFrequency> frequencies = keywordWithFrequencyDao.findAllFrequencyByKeyword(topTenKeyword);
            keywordsWithFrequency.add(frequencies);
        }
        return keywordsWithFrequency;
    }

    /**
     * 根据顶会获取数据库中在要查询顶会出现频数最高的十个关键词，出现年份及其各年出现频数，所属会议
     *
     * @param source the source 顶会名称
     * @return the list 顶会对应出现频率最高的十个关键词，及年份，频数，来源
     */
    public List<List<KeywordWithFrequency> > findKeywordsFrequencyBySource(String source) {
        List<String> topTenKeywords = keywordDao.findTopTenFrequencyKeywordBySource(source);
        List<List<KeywordWithFrequency> > keywordsWithFrequency = new LinkedList<>();
        for (String topTenKeyword : topTenKeywords) {
            List<KeywordWithFrequency> frequencies = keywordWithFrequencyDao.findFrequencyByKeywordAndSource(
                    topTenKeyword,source);
            keywordsWithFrequency.add(frequencies);
        }
        return keywordsWithFrequency;
    }
}
