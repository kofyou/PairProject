package dao;

import pojo.Paper;

public interface PaperDAO {
    Paper getByTitle(String s);
    Paper getByKeyword(String s);
    Paper getByType(String s);

}
