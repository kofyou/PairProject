package com.getInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mysql.jdbc.log.Log;

import jdk.nashorn.internal.parser.Lexer;

public class GetJson {

	public static void readJsonFile(String filePath) {
		BufferedReader reader = null;
		String readJson = "";

		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				readJson += tempString;

				JSONObject jsonObject = JSONObject.parseObject(readJson);

				Map<String, String> stringMap = new HashMap<>();
				Set<String> keys = jsonObject.keySet();
				Iterator<String> iterator = keys.iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					//System.out.printf(key + " \n");
					stringMap.put(key, jsonObject.getString(key));
				}
				String id = stringMap.get("articleId");
				System.out.println("文章id："+id+"\n");
				
				String title = stringMap.get("title");
				System.out.println("文章标题："+title+"\n");
				
				String about = stringMap.get("doi");
				System.out.println("文章论坛相关："+about+"\n");
				
				String keyword = stringMap.get("keywords");
				System.out.println("文章关键词："+keyword+"\n");
				
				String url = stringMap.get("doiLink");
				System.out.println("文章链接："+url+"\n");
				
				


			}

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
}
