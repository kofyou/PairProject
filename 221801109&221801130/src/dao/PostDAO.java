package dao;

import pojo.Post;

import java.util.List;



public interface PostDAO {
    int getTotal();
    void add(Post Post);
    void update(Post user);
    void delete(String title) ;
    Post get(int id);
    List<Post> list();
    List<Post> list(int start, int count) ;
    List<Post> list(String text);
    boolean isExist(String name) ;
    Post get(String name) ;
    Post get(String name, String password) ;
}