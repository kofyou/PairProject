package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.KeywordWithFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordWithFrequencyDao extends JpaRepository<KeywordWithFrequency,String> {

    /**
     * 根据关键词获取其在所有会议中出现的年份，及该年的出现频率（包含关键词，会议类别，出现年份，字段）
     *
     * @param keyword the keyword 要查询的关键词
     * @return the list 包含该关键词出现年份及频率的列表（完整包含关键词，会议类别，出现年份，字段四个字段）
     */
    @Query(value = "select distinct keyword,'ALL' source,publishYear,sum(frequency) frequency " +
            "from keyword_with_frequency " +
            "where keyword = ?1 " +
            "group by publishYear " +
            "order by publishYear",nativeQuery = true)
    List<KeywordWithFrequency>  findAllFrequencyByKeyword(String keyword);

    /**
     * 根据关键词获取其在要查询的会议中出现的年份，及该年的出现频率（包含关键词，会议类别，出现年份，字段）
     *
     * @param keyword the keyword 要查询的关键词
     * @param source  the source 要查询的会议
     * @return the list 包含该关键词出现年份及频率的列表（完整包含关键词，会议类别，出现年份，字段四个字段）
     */
    @Query(value = "select keyword,source,publishYear,frequency " +
            "from keyword_with_frequency " +
            "where keyword = ?1 " +
            "and source = ?2 " +
            "GROUP BY publishYear " +
            "ORDER BY publishYear",nativeQuery = true)
    List<KeywordWithFrequency> findFrequencyByKeywordAndSource(String keyword,String source);
}
