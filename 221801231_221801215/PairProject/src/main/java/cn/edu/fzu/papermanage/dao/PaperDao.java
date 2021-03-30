package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperDao extends JpaRepository<Paper,Integer> {
    /**
     * 通过标题查找论文信息
     *
     * @param titleProcessed 要查找的论文题目，可以插入%进行模糊查询
     * @return the list 包含完整信息的Paper列表
     */
    @Query(value = "select DISTINCT * from papers where title like ?1",nativeQuery = true)
    List<Paper> findPapersByTitle(String titleProcessed);

    /**
     * 通过输入模糊查询所有论文标题，摘要，关键词
     *
     * @param fuzzyContent the fuzzy content 模糊查询的内容
     * @return the list 论文列表（不包含关键词）
     */
    @Query(value = "select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from papers " +
            "where title like ?1 " +
            "UNION " +
            "select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from papers " +
            "where abstract like ?1 " +
            "UNION " +
            "select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from papers,keywords " +
            "where keywords.keyword like ?1 " +
            "and keywords.paperId = papers.id",nativeQuery = true)
    List<Paper> fuzzyFindPaperByKeywordOrTitleOrAbstract(String fuzzyContent);

    /**
     * Fuzzy find paper by keyword or title or abstract and page list.
     *
     * @param fuzzyContent the fuzzy content
     * @param offset       the offset
     * @param pageSize     the page size
     * @return the list
     */
    @Query(value = "(select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from papers " +
            "where title like ?1) " +
            "UNION " +
            "(select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from papers " +
            "where abstract like ?1) " +
            "UNION " +
            "(select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from papers,keywords " +
            "where keywords.keyword like ?1 " +
            "and keywords.paperId = papers.id) " +
            "limit ?2,?3",nativeQuery = true)
    List<Paper> fuzzyFindPaperByKeywordOrTitleOrAbstractAndPage(String fuzzyContent,Integer offset,Integer pageSize);

    /**
     * 通过用户id查找其关联的所有论文（不包括关键词）
     *
     * @param userId the user id 用户id
     * @return the list 用户关联的论文列表（不包括关键词）
     */
    @Query(value = "select distinct papers.id,papers.title,papers.source,papers.url,papers.publishyear,papers.abstract " +
            "from papers,user_paper " +
            "where user_paper.userid = ?1 and user_paper.paperId =papers.id",nativeQuery = true)
    List<Paper> findAllPapersByUserId(Integer userId);

    /**
     * 根据关键词获取用户包含此关键词的论文内容（不包括关键词，可分页）
     *
     * @param userId      the user id 用户id
     * @param keyword     the keyword 关键词
     * @param pageRequest the page request 分页信息
     * @return the page 分页后的论文内容列表页
     */
    @Query(value = "select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from user_paper,papers,keywords " +
            "where user_paper.userId = ?1 " +
            "and user_paper.paperId = papers.id " +
            "and papers.id = keywords.paperId " +
            "and keywords.keyword like ?2",nativeQuery = true)
    Page<Paper> findUserPapersByKeyword(Integer userId,String keyword, Pageable pageRequest);

    /**
     * 根据用户id查询所有用户关联的论文（不包含关键词）
     *
     * @param userId   the user id 用户id
     * @param offset   the offset
     * @param pageSize the page size 起始序号
     * @return the page 用户关联的论文列表页（不包含关键词）
     */
    @Query(value = "select distinct papers.id,papers.title,papers.source,papers.url,papers.publishYear,papers.abstract " +
            "from user_paper " +
            "inner join papers on user_paper.paperId = papers.id " +
            "where user_paper.userId = ?1 " +
            "limit ?2,?3 ",nativeQuery = true)
    List<Paper> findAllUserPapersByPage(Integer userId,Integer offset,Integer pageSize);
}
