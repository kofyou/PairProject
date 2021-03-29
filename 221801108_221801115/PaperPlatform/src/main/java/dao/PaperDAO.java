package dao;

import pojo.Keyword;
import pojo.Paper;
import pojo.YearFrequency;

import java.util.ArrayList;
import java.util.List;

public interface PaperDAO {

    int getTotal(String str);

    void delete(String title);

    ArrayList<Paper> list(String str, int pageNum, int lineNum);

    Paper get(String title);

}
