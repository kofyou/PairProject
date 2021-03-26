package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.UserPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaperDao extends JpaRepository<UserPaper,Integer> {
}
