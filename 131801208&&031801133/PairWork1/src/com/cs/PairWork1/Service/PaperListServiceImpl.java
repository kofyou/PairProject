package com.cs.PairWork1.Service;

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
	public static void main(String[] args) {
		
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
			
			for(int i = 0 ; i < datalist.size() ; i++) {
				  System.out.println(datalist.get(i).getAbout());
			}
		

	}

}
