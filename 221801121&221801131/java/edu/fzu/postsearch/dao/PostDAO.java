package edu.fzu.postsearch.dao;
import edu.fzu.postsearch.pojo.Post;

import java.util.List;

public interface PostDAO {
    int getTotal();
    void add(Post post);
    void update(Post post);
    void delete(String title) ;
    Post get(String title);
    List<Post> list();
    List<Post> list(int start, int count) ;
    boolean isExist(String name) ;
}
