package com.cs.PairWork1.Utils;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



public class GetJson {
	private String id;
	private String title;
	private String url;
	private String about;
	private String keywords;
	public  void readJsonFile(String filePath) {
		BufferedReader reader = null;
		String readJson = "";

		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			
			String tempString = null;
			
			while ((tempString = reader.readLine()) != null) {
				readJson += tempString;	
			}
			
				readJson=readJson.substring(0, readJson.length()-1);	
				
				JSONObject jsonObject = JSONObject.parseObject(readJson);
				Map<String, String> stringMap = new HashMap<>();
				Set<String> keys = jsonObject.keySet();
				Iterator<String> iterator = keys.iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					//System.out.printf(key + " \n");
					stringMap.put(key, jsonObject.getString(key));
				}
				
				//System.out.println(readJson);
				id = stringMap.get("articleId");
				//System.out.println("文章id："+id+"\n");
				
				title = stringMap.get("title");
				//System.out.println("文章标题："+title+"\n");
				
				about = stringMap.get("doi");
				//System.out.println("文章论坛相关："+about+"\n");
							
				
				keywords = stringMap.get("keywords");
				//System.out.println("文章关键词："+keywords+"\n");
				
				url = stringMap.get("doiLink");
				//System.out.println("文章链接："+url+"\n");	
				
				/*JSONArray picArray=JSONArray.parseArray(readJson.toString().trim());
				//JSONArray picArray = JSONArray.parseArray(readJson);
				if (picArray != null) {
					for(int i = 0; i < picArray.size(); i++) {
					JSONObject jsonObject1 = picArray.getJSONObject(i);
					String pic = jsonObject1.getString("picture");
				 	System.out.println(pic);
				}}*/
				
			
			
			
			// System.out.println(jsonObject.toJSONString());
			/*
			 * for (Map<String, Object> map : list) { for (String s : map.keySet()) {
			 * System.out.print(map.get(s) + "  "); } }
			 */
			// System.out.println(readJson.length());
//							System.out.println(readJson.toString());
			/*
			 * JSONObject jsonObject = JSONObject.parseObject(readJson);
			 * System.out.println(JSON.toJSONString(jsonObject));
			 */
			
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
	public String getId() {
		return this.id;
	}
	
	public String getUrl() {
		return this.url;
	}
	public String getTitle() {
		return this.title;
	}
	public String getKeywords() {
		return this.keywords;
	}
	public String getAbout() {
		return this.about;
	}
	
	
}
