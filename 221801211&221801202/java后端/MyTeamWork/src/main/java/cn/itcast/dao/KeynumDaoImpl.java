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

    public Map<String,Integer> add() {
        List<Keynum> list = new ArrayList<Keynum>();
        List<String> keylist = new ArrayList<String>();
        String sql = "select * from paperinfo";
        try {
            connection = DBUtil1.getConnection();   //优化
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String key = resultSet.getString("keywords");
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
    }


}

