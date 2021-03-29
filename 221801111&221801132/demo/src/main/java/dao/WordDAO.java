package dao;

import pojo.Paper;

import java.util.List;

public interface WordDAO {
    List<Paper> listGetByYear(String s);
}
