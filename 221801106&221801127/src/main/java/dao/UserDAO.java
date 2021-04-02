package dao;

import baseclass.Paper;
import baseclass.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDAO {
    List<Paper> findPaper(String input) throws SQLException;  //模糊查询从论文库里面找论文
    List<Paper> findUserPaper(String username) throws SQLException; //找用户收藏的论文

    User findUser(String name,String password)throws SQLException;  //找是否存在这个用户，是的话返回用户类，不是的话返回null
    boolean InsertUser(String name,String password)throws SQLException; //插入用户
    public void updateUserInfo(List<String> keys,List<String> values,String name) throws SQLException;  //修改用户单个信息(一次性多个字段修改，名字得放最后)

    public boolean UserInsertPaper(String username,int paperNumber) throws SQLException;  //添加用户收藏论文
    public void UserDeletePaper(String username,int paperNumber) throws SQLException;  //删除用户类收藏论文
    public List<String> returnUserTenHotwords(String username) throws SQLException;  //用来统计返回用户收藏论文里面的十大关键热词

    public void writeTenhotwordsIntoDB(List<Map.Entry<String,Integer>> tenhotwords,String type) throws SQLException;  //将总的和三大会议的十大热词存到数据库里面
    public List<Map.Entry<String,Integer>> getTenHotwords(String type) throws SQLException;  //读取十大热词
}
