package com.getInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import jdk.nashorn.internal.parser.Lexer;

public class GetJson {
	
	 
		      
			 public static void readJsonFile(String filePath){
			        BufferedReader reader = null;
			        String readJson = "";
			        
			            try {
							FileInputStream fileInputStream = new FileInputStream(filePath);
							InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
							reader = new BufferedReader(inputStreamReader);
							String tempString = null;
							while ((tempString = reader.readLine()) != null){
							    readJson += tempString;
							    
							}
							//List<Map<String, Object>> list = JSONObject.parseObject(readJson, new TypeReference<List<Map<String, Object>>>() {} );
							JSONObject jsonObject = JSONObject.parseObject(readJson);
							
							//JSONArray jsonArray = JSONArray.fromObject(jsonObject);
							
							
							System.out.println(jsonObject.toJSONString());
							/*for (Map<String, Object> map : list) {
					            for (String s : map.keySet()) {
					                System.out.print(map.get(s) + "  ");
					            }
					        }*/
							System.out.println(readJson.length());
//							System.out.println(readJson.toString());
							/*JSONObject jsonObject = JSONObject.parseObject(readJson);
				            System.out.println(JSON.toJSONString(jsonObject));*/
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			        
			    }	   
}
