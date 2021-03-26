package dao;

import pojo.Paper;

import java.util.ArrayList;
import java.util.List;

public interface PaperDAO {
    int getTotal(String str);
    void add(Paper paper);
    void update(Paper paper);
    void delete(String title);
    ArrayList<Paper> list(String str, int pageNum, int lineNum);
    List<Paper> list(int start, int count);
    boolean isExist(String str);
    Paper get(String title);
}
