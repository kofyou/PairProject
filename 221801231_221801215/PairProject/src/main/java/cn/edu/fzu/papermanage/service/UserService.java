package cn.edu.fzu.papermanage.service;

import cn.edu.fzu.papermanage.dao.UserDao;
import cn.edu.fzu.papermanage.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户类
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 修改、插入用户
     *
     * @param user the user
     */
    public void save(User user) {
        userDao.save(user);
    }

    /**
     * 通过账号查找用户
     *
     * @param account the account
     * @return the user
     */
    public User findUserByAccount(String account) {
        return userDao.findUserByAccount(account);
    }
}
