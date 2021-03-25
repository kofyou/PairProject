package cn.edu.fzu.papermanage.dao;


import cn.edu.fzu.papermanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户类Dao
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * 通过账户查找用户
     *
     * @param account the account
     * @return the user
     */
    @Query(value = "select * from user where account = ?1 ",nativeQuery = true)
    User findUserByAccount(String account);

}
