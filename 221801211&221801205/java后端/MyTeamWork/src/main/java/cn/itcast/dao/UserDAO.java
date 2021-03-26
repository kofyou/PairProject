package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

public interface UserDAO {
    List<User> selectUsers();
    void add(User user);
    void delete(User user);
    User getinfo(int id);

}
