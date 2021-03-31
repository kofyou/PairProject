package com.cs.PairWork1.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.cs.PairWork1.Dao.Paper;

public class KeyWord {
	public static void main(String[] args) {
		String sql="select keyword from paper";
		DBUtils dbUtils = new DBUtils();
		dbUtils.init();
		Connection conn =dbUtils.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		
		try {
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			String readJson="";
			while(rs.next()) {
				String tempString=rs.getString("keyword");
					readJson += tempString;																				
			}	
			JSONObject jsonObject = JSONObject.parseObject(readJson);
			Map<String, String> stringMap = new HashMap<>();
			Set<String> keys = jsonObject.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				System.out.printf(key + " \n");
				stringMap.put(key, jsonObject.getString(key));
			}					
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
