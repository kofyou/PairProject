package dao;

import pojo.User;

import java.util.List;

public interface UserDAO {
    int getTotal();
    void add(User user);
    void update(User user);
    boolean delete(int id) ;
    User get(int id);
    List<User> oblist(int cur, String search);
    List<User> list(int start, int count) ;
    boolean isExist(String name) ;
    User get(String name) ;
    User get(String name, String password) ;
}