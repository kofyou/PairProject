package com.main;

import com.getInfo.GetJson;

public class Main {
	public static void main(String[] args) {
		//String s="F:\\2000_3-D model construction using range and image data.json";
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		String s="F://2000_3-D model construction using range and image data.json";
		GetJson g= new GetJson();
		g.readJsonFile(s);
	}
}
