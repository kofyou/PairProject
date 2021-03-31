package dao;

import pojo.Keyword;
import pojo.Paper;
import pojo.YearFrequency;

import java.util.ArrayList;
import java.util.List;

public interface PaperDAO {

    /* 返回模糊查询到的论文总数 */
    int getTotal(String str);

    /* 根据论文题目删除对应论文 */
    void delete(String title);

    /* 模糊查询分页用 */
    ArrayList<Paper> list(String str, int pageNum, int lineNum);

    /* 根据论文题目返回对应论文 */
    Paper get(String title);

}
