package dao;

import baseclass.Paper;
import baseclass.User;
import util.BaseUtil;
import util.DBUtil;

import java.sql.*;
import java.util.*;

public class UserDAOImpl implements UserDAO {


    public List<Paper> findUserPaper(String username) throws SQLException {//根据用户名查询用户收藏的论文(分隔符，sql语句，resultset.next()要注意)
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("select papers from user where name = '%s'", username);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String result = resultSet.getString("papers");
        if (result.equals(""))
            return null;

        String[] papersNumbers = result.split(",");

        List<Paper> paperList = new ArrayList<>();
        for (String papernumebr : papersNumbers) {
            sql = String.format("select * from papers where number = %s", papernumebr);
            resultSet = statement.executeQuery(sql);
            resultSet.next();

            Paper paper = new Paper();
            paper.论文名称 = resultSet.getString("title");
            paper.ID = resultSet.getInt("number");
            paper.摘要 = resultSet.getString("abstract");
            paper.会议和年份 = resultSet.getString("meetingtype") + " " + resultSet.getString("meetingyear");
            paper.发布时间 = resultSet.getString("releasetime");
            paper.原文链接 = resultSet.getString("link");
            String[] keywords = resultSet.getString("keywords").split(",");
            paper.关键词 = keywords;

            paperList.add(paper);
        }
        statement.close();
        connection.close();
        return paperList;
    }

    public List<Paper> findPaper(String input) throws SQLException {//模糊查询（目前只针对作者 ）
        List<Paper> papers = new ArrayList<>();

        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from papers where title like ? or abstract like ? or keywords like ?");
        preparedStatement.setString(1,"%"+input+"%");
        preparedStatement.setString(2,"%"+input+"%");
        preparedStatement.setString(3,"%"+input+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Paper paper = new Paper();
            paper.论文名称 = resultSet.getString("title");
            paper.ID = resultSet.getInt("number");
            paper.摘要 = resultSet.getString("abstract");
            paper.会议和年份 = resultSet.getString("meetingtype") + " " + resultSet.getString("meetingyear");
            paper.发布时间 = resultSet.getString("releasetime");
            paper.原文链接 = resultSet.getString("link");
            String[] keywords = resultSet.getString("keywords").split(",");
            paper.关键词 = keywords;
            papers.add(paper);
        }

        preparedStatement.close();
        connection.close();
        return papers;
    }

    public User findUser(String name, String password) throws SQLException {  //登入界面的时候根据用户名和密码登入验证
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("select * from user where name = '%s' && password = '%s'", name, password);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
            user.setPapers(resultSet.getString(3));
            user.setNickname(resultSet.getString(4));
            user.setSchool(resultSet.getString(5));
            user.setProfession(resultSet.getString(6));
            user.setPhonenumber(resultSet.getString(7));

            statement.close();
            connection.close();
            return user;
        } else {
            statement.close();
            connection.close();
            return null;
        }
    }

    public boolean InsertUser(String name, String password) throws SQLException {  //登入界面的时候根据用户名和密码登入验证
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("select * from user where name = '%s'", name);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            statement.close();
            connection.close();
            return false;
        } else {
            sql = String.format("insert into user values ('%s','%s','%s','%s','%s','%s','%s')", name, password, "","","","","");
            statement.execute(sql);
            statement.close();
            connection.close();
            return true;
        }
    }

    @Override
    public boolean UserInsertPaper(String username, int paperNumber) throws SQLException {  //concat没有用好，变量看错，没写statement.execute（）
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("select papers from user where name = '%s'", username);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String papers = "";
        papers  =  resultSet.getString("papers");

        if (!papers.equals("")){  //判断是否已经收藏
            String[] temp = papers.split(",");
            for (String paper : temp){
                if (paper.equals(Integer.toString(paperNumber)))
                    return false;
            }
        }

        papers = paperNumber+","+papers;

        sql = String.format("update user set papers = '%s' where name = '%s'", papers, username);
        statement.execute(sql);
        statement.close();
        connection.close();
        return true;
    }

    @Override
    public void UserDeletePaper(String username, int paperNumber) throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format("select papers from user where name = '%s'", username);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String paper = resultSet.getString("papers");
        paper = paper.replaceFirst(Integer.toString(paperNumber) + ",", "");

        sql = String.format("update user set papers = '%s' where name = '%s'", paper, username);
        statement.execute(sql);
        statement.close();
        connection.close();
        return;
    }

    @Override
    public List<String> returnUserTenHotwords(String username) throws SQLException {
        List<Paper> papers = findUserPaper(username);
        if (papers == null){
            return null;
        }
        HashMap<String, Integer> hotwords = new HashMap<>();

        for (Paper paper : papers) {
            HashMap<String, Integer> new_hotwords = BaseUtil.parseHashmap(paper.关键词);
            BaseUtil.merge(hotwords, new_hotwords);
        }

        List<Map.Entry<String, Integer>> list = BaseUtil.sortHashMap(hotwords);
        List<String> tenHotwords = new ArrayList<>();
        for (int i = 0; i < 10 && i < list.size(); i++) {
            tenHotwords.add(list.get(i).getKey());
        }
        return tenHotwords;
    }

    public void writeTenhotwordsIntoDB(List<Map.Entry<String,Integer>> tenhotwords,String type) throws SQLException{
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into tenhotwords values (?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,type);
        for (int i=1;i<=10;i++){
            preparedStatement.setString(i+1,tenhotwords.get(i-1).getKey()+":"+tenhotwords.get(i-1).getValue());
        }
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void updateUserInfo(List<String> keys, List<String> values ,String name) throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        for (int i=0;i<keys.size();i++){
            String sql = String.format("update user set %s = '%s' where name = '%s'",keys.get(i),values.get(i),name);
            statement.execute(sql);
        }
        statement.close();
        connection.close();
        return;
    }

    @Override
    public List<Map.Entry<String, Integer>> getTenHotwords(String type) throws SQLException {
        List<Map.Entry<String,Integer>> list = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<>();
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        for (int i=1;i<=10;i++){
            String sql = String.format("select %s from tenhotwords where type = '%s'","word"+i,type);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                String[] temp  = resultSet.getString(1).split(":");
                hashMap.put(temp[0],Integer.parseInt(temp[1]));
            }
        }

        list = BaseUtil.sortHashMap(hashMap);
        statement.close();
        connection.close();
        return list;
    }





    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAOImpl();

        List<String> list = userDAO.returnUserTenHotwords("郑龙海");
        for (String s : list){
            System.out.println(s);
        }
    }
}
