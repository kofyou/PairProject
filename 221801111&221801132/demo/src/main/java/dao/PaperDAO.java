package dao;

import pojo.Paper;

import java.util.List;

public interface PaperDAO {
    List<Paper> listGetByTitle(String s);
    List<Paper> listGetByKeyword(String s);
    List<Paper> listGetByType(String s);

}
