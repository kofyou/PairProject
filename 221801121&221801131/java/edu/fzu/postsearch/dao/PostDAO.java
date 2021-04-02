package edu.fzu.postsearch.dao;
import edu.fzu.postsearch.pojo.Post;

import java.util.List;

public interface PostDAO {
    int getTotal();
    void add(Post post);
    void update(Post post);
    void delete(int id) ;
    Post get(String title);
    List<Post> list();
    public List<Post> listSearch(String search);
    List<Post> list(int start, int count) ;
    boolean isExist(String name) ;
}
