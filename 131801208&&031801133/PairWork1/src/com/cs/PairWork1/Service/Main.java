package com.cs.PairWork1.Service;

import com.cs.PairWork1.Utils.GetJson;

public class Main {
	public static void main(String[] args) {
		//String s="F:\\2000_3-D model construction using range and image data.json";
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		String s="F://2000_3-D model construction using range and image data.json";
		GetJson g= new GetJson();
		g.readJsonFile(s);
	}
}
