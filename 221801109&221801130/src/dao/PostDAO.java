package dao;

import pojo.Post;
import java.util.List;

public interface PostDAO {
    int getTotal();
    void delete(String title) ;
    List<Post> list();
    List<Post> list(String text);
}