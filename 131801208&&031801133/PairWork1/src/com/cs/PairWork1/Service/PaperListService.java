package com.cs.PairWork1.Service;

import java.io.File;
import java.util.List;

import com.cs.PairWork1.Dao.Paper;

public interface PaperListService {
	  public List<Paper> listAll();
	  //public  List<Paper> getECCV(File file);
	  public  List<Paper> find(String s);
	  public  int delete(String title);
	  public  List<Paper> getDirectory(File file);
	  
}
