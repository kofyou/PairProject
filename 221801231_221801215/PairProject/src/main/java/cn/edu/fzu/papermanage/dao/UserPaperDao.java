package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.UserPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserPaperDao extends JpaRepository<UserPaper,Integer> {
    /**
     * 根据用户id和论文id从用户关联的论文表中删除关联记录
     *
     * @param userId  the user id 需要删除的用户id
     * @param paperId the paper id 要删除的论文id
     */
    @Modifying
    @Transactional
    @Query(value = "delete from user_paper where userId = ?1 and paperId = ?2",nativeQuery = true)
    void deleteUserPaperByUserIdAndPaperId(Integer userId,Integer paperId);
}
