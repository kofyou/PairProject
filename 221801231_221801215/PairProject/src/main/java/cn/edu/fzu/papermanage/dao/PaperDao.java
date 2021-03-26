package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.Paper;
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
}
