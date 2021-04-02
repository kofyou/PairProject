package cn.edu.fzu.papermanage.service;

import cn.edu.fzu.papermanage.dao.KeywordDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeywordService {
    @Resource
    private KeywordDao keywordDao;

    /**
     * 根据用户id获取其关联论文的出现频率最高的前十个关键词
     *
     * @param userId the user id 用户id
     * @return the users top ten keyword by user id
     *          出现频率最高的前十个关键词字符串列表，热度由高到低排列
     */
    public List<String> getUsersTopTenKeywordByUserId(Integer userId) {
        return keywordDao.findTopTenFrequencyKeywordByUserId(userId);
    }
}
