package dao;

import pojo.Keyword;
import pojo.Paper;
import pojo.YearFrequency;

import java.util.ArrayList;

public interface KeywordDAO {

    int getTotal(String keyword);

    ArrayList<Paper> keyList(String keyword,int pageNum, int lineNum);

    ArrayList<Keyword> GetTop10Keywords();

    ArrayList<YearFrequency> getKeywordFrequencyEachYear(String conference);

    int keyListNum(String keyword, int year, String conference);
}
