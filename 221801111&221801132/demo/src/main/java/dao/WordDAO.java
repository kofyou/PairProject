package dao;

import pojo.KeyWord;

import java.util.List;

public interface WordDAO {
    List<KeyWord> listGetByYear2012CVPR();
    List<KeyWord> listGetByYearCVPR(String s);
    List<KeyWord> listGetByYearICCV(String s);
    List<KeyWord> listGetByYearECCV(String s);
}
