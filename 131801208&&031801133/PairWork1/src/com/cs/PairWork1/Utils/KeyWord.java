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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String readJson="";
		System.out.printf("begin");
		ArrayList<String> list = new ArrayList<String>();
		try {
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();			
			while(rs.next()) {
				String tempString=rs.getString("keyword");
					readJson += tempString;
					Pattern p1=Pattern.compile("\"(.*?)\"");
					 
					Matcher m = p1.matcher(readJson);
					 StringBuilder stringBuilder = new StringBuilder();					
					 while (m.find()) {
						 list.add(m.group().trim().replace("\"","")+" ");	
					     
					}		
					 System.out.println(list.toString());				
			}	
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*JSONObject jsonObject = JSONObject.parseObject(readJson);
		Map<String, String> stringMap = new HashMap<>();
		Set<String> keys = jsonObject.keySet();
		Iterator<String> iterator = keys.iterator();
		System.out.printf("begin1");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.printf(key + " \n");
			stringMap.put(key, jsonObject.getString(key));
		}		*/					
	}
}
