package com.cs.PairWork1.Service;

import java.io.File;

import com.cs.PairWork1.Utils.GetJson;

public class Main {
	public static void main(String[] args) {
		
		//String s="JsonFileLib//2000_3-D model construction using range and image data.json";
		//String s="F://2000_3-D model construction using range and image data.json";
		//File file = new File("F:\论文数据\CVPR（2000年至2020年，6916篇");
		//File file = new File("F:\论文数据\ECCV（2016至2020，3033份）");
		File file = new File("F:\\论文数据\\ICCV（2001年至2019年，3196篇）");
        showDirectory(file);
		
		
	}
	public static void showDirectory(File file){
		File[] fileCVPR=file.listFiles();
		for(File file1 : fileCVPR) {
			String s=(file1.getAbsolutePath());
			GetJson g= new GetJson();
			g.readJsonFile(s,1);
			if(file1.isDirectory()){
	            showDirectory(file1);
	        }
		}
		
    }

}
