package com.cs.PairWork1.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cs.PairWork1.Dao.Paper;
import com.cs.PairWork1.Utils.GetJson;

public class PaperListServiceImpl implements PaperListService {

	@Override
	public List<Paper> listAll() {
		
		List<Paper> datalist = new ArrayList<Paper>();
		Paper p= new Paper();
		String s="F://2000_3-D model construction using range and image data.json";
		GetJson gj= new GetJson();
		gj.readJsonFile(s);
		p.setAbout(gj.getAbout());
		p.setId(gj.getId());
		p.setKeywords(gj.getKeywords());
		p.setTitle(gj.getTitle());
		p.setUrl(gj.getUrl());
		
		datalist.add(p);
		return datalist;
	}
	//below used for test
	public static List<Paper> getDirectory(File file){
		File[] fileCVPR=file.listFiles();
		List<Paper> datalist = new ArrayList<Paper>();
		int i=0;
		for(File file1 : fileCVPR) {
			i++;
			/*if(i>10) {
				break;
			}*/
			String s=(file1.getAbsolutePath());
			GetJson g= new GetJson();
			g.readJsonFile(s);			
			Paper p= new Paper();
			p.setAbout(g.getAbout());
			p.setId(g.getId());
			//p.setKeywords(g.getKeywords());
			p.setTitle(g.getTitle());
			p.setUrl(g.getUrl());			
			datalist.add(p);
						
			if(file1.isDirectory()){
	            getDirectory(file1);
	        }
		}
		return datalist;
		
    }
	public static void main(String[] args) {
		

			//File file = new File("F:\论文数据\CVPR（2000年至2020年，6916篇");
			//File file = new File("F:\论文数据\ECCV（2016至2020，3033份）");
			File file = new File("F:\\论文数据\\ICCV（2001年至2019年，3196篇）");
			List<Paper> datalist = getDirectory(file);	        					
			for(int i = 0 ; i < datalist.size() ; i++) {
				  System.out.println(datalist.get(i).getAbout());
			}
		

	}

}
