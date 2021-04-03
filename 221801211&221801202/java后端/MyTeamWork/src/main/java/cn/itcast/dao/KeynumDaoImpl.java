package cn.itcast.dao;

import cn.itcast.domain.PaperInfo;
import cn.itcast.unit.DBUtil;
import cn.itcast.unit.DBUtil1;
import org.apache.commons.dbcp2.BasicDataSource;
import cn.itcast.domain.Keynum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class KeynumDaoImpl implements KeynumDao{
    Connection connection;
    PreparedStatement preparedStatement;
    DBUtil1 dbUtil;
    BasicDataSource basicDataSource;
    ResultSet resultSet;

    @Override
    public Map<String, Integer> add() {
        return null;
    }

    /*public Map<String,Integer> add() {
        List<Keynum> list = new ArrayList<Keynum>();
        List<String> keylist = new ArrayList<String>();
        String sql = "select * from paperinfo";
        try {
            connection = DBUtil1.getConnection("keywords");   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String key = resultSet.getString("keyword");
                keylist.add(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        DBUtil1.close(resultSet,preparedStatement,connection);
    }
        if(keylist ==null || keylist.size()==0) return null;
        Map<String,Integer> map= new HashMap<String, Integer>();
        for(String str : keylist){
            Integer count = map.get(str);
            map.put(str,(count== null)?1:count+1);
        }
        return map;
    }

    public List<Keynum> addKeynums(Map<String,Integer> map) {
        List<Keynum> keynums = new ArrayList<Keynum>();
        for(String value : map.keySet()){
            Keynum keynum = new Keynum();
            keynum.setKeyword(value);
            keynum.setAppeartimes(map.get(value));
            keynums.add(keynum);
        }
        Collections.sort(keynums, new Comparator<Keynum>() {
            @Override
            public int compare(Keynum o1, Keynum o2) {
                return Integer.compare(o2.getAppeartimes(), o1.getAppeartimes());
            }
        });
        return keynums;
    }*/

    public List<Keynum> selectkey() {
        List<Keynum> list = new ArrayList<Keynum>();
        String sql = "select * from keywords";
        try {
            connection = DBUtil1.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Keynum keynum = new Keynum();
                keynum.setKeyword(resultSet.getString("keyword"));
                keynum.setAppeartimes(resultSet.getInt("appeartimes"));
                list.add(keynum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil1.close(resultSet, preparedStatement, connection);
        }
        Collections.sort(list, new Comparator<Keynum>() {
            @Override
            public int compare(Keynum o1, Keynum o2) {
                return Integer.compare(o2.getAppeartimes(), o1.getAppeartimes());
            }
        });
        List<Keynum> keynumList = new ArrayList<Keynum>();
        int i=0;
        for(Keynum keynum : list)
        {
            i++;
            if(i<11)
            {
                keynumList.add(keynum);
            }
            else break;
        }
        return keynumList;
    }

    public List<Keynum> selectname(String meeting) {
        List<String> list = new ArrayList<String>();
        String sql = "select * from keywords where year = "+ "2020" + "and" + "meeting = "+ meeting;
        try {
            connection = DBUtil1.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil1.close(resultSet, preparedStatement, connection);
        }
        return selectkey2020(select2020(list));
    }

    public List<String> select2020(List<String> name) {
        List<String> list = new ArrayList<String>();
        int i=0;
        String sql="select * from name_keyword where name =";
        for(String str : name)
        {
            if(i<name.size()-1)
            {
                sql += str+" or name =";
            }
            else sql += str;
        }
        try {
            connection = DBUtil1.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String keyword = resultSet.getString("keyword");
                list.add(keyword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil1.close(resultSet, preparedStatement, connection);
        }
        return list;
    }

    public List<Keynum> selectkey2020(List<String> keywords) {
        List<Keynum> list = new ArrayList<Keynum>();
        int i=0;
        String sql="select * from keywords where keyword =";
        for(String str : keywords)
        {
            if(i<keywords.size()-1)
            {
                sql += str+" or keyword =";
            }
            else sql += str;
        }
        try {
            connection = DBUtil1.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Keynum keynum = new Keynum();
                keynum.setKeyword(resultSet.getString("keyword"));
                keynum.setAppeartimes(resultSet.getInt("appeartimes"));
                list.add(keynum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil1.close(resultSet, preparedStatement, connection);
        }
        Collections.sort(list, new Comparator<Keynum>() {
            @Override
            public int compare(Keynum o1, Keynum o2) {
                return Integer.compare(o2.getAppeartimes(), o1.getAppeartimes());
            }
        });
        List<Keynum> keynumList = new ArrayList<Keynum>();
        int x=0;
        for(Keynum keynum : list)
        {
            x++;
            if(x<11)
            {
                keynumList.add(keynum);
            }
            else break;
        }
        return keynumList;
    }

}