package dao;

import pojo.Paper;

import java.util.ArrayList;
import java.util.List;

public interface PaperDAO {
    int getTotal();
    void add(Paper paper);
    void update(Paper paper);
    void delete(int id);
    ArrayList<Paper> list(String str);
    List<Paper> list(int start, int count);
    boolean isExist(String str);
    Paper get(String title);
}
