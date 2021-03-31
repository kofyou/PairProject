package com.cs.PairWork1.Service;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cs.PairWork1.Dao.Paper;
import com.cs.PairWork1.Utils.DBUtils;
import com.cs.PairWork1.Utils.GetJson;


public class PaperListServiceImpl implements PaperListService {

	/*private static DBUtils dbUtils;
	public void setDBUtils(DBUtils dbUtils) {
		this.dbUtils=dbUtils;
	}*/
	
	@Override
	public List<Paper> listAll() {
		
		String sql = "select * from paper";
		DBUtils dbUtils = new DBUtils();
		dbUtils.init();
		Connection conn =dbUtils.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		
		try {
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			List<Paper> datalist = new ArrayList<Paper>();
			while(rs.next()) {
				Paper p= new Paper();
				p.setAbout(rs.getString("about"));
				p.setId(rs.getString("paperid"));
				//p.setKeywords(g.getKeywords());
				p.setTitle(rs.getString("title"));
				p.setUrl(rs.getString("url"));	
								
				datalist.add(p);
			}
			return datalist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	//below used for test
	
	public static  List<Paper> getECCV(File file){//test method do not mind
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
			g.readJsonFile(s,2);			
			Paper p= new Paper();
			p.setAbout(g.getAbout2());
			p.setId(g.getId2());
			//p.setKeywords(g.getKeywords());
			p.setTitle(g.getTitle2());
			p.setUrl(g.getUrl2());			
			datalist.add(p);
						
			if(file1.isDirectory()){
	            getECCV(file1);
	        }
		}
		return datalist;
		
    }
	public  List<Paper> find(String s){
		String sql = "select * from paper where paperid=?";
		DBUtils dbUtils = new DBUtils();
		dbUtils.init();
		Connection conn =dbUtils.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, s);
			rs=pstm.executeQuery();
			List<Paper> datalist = new ArrayList<Paper>();
			while(rs.next()) {
				Paper p= new Paper();
				p.setAbout(rs.getString("about"));
				p.setId(rs.getString("paperid"));
				//p.setKeywords(g.getKeywords());
				p.setTitle(rs.getString("title"));
				p.setUrl(rs.getString("url"));									
				datalist.add(p);
			}
			return datalist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	public  int delete(String title) {
		String sql="delete from paper where title=?";
		DBUtils dbUtils = new DBUtils();
		dbUtils.init();
		Connection conn =dbUtils.getConn();
		PreparedStatement pstm = null;
		
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.executeUpdate();			
			return 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public  List<Paper> getDirectory(File file){//test method do not mind
		File[] fileCVPR=file.listFiles();
		List<Paper> datalist = new ArrayList<Paper>();
		int i=0;
		for(File file1 : fileCVPR) {
			i++;
			
			String s=(file1.getAbsolutePath());
			GetJson g= new GetJson();
			g.readJsonFile(s,1);			
			Paper p= new Paper();
			p.setAbout(g.getAbout());
			p.setId(g.getId());
			p.setKeywords(g.getKeywords());
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
			//test method do not mind

			//File	file = new File("F:\\论文数据\\CVPR（2000年至2020年，6916篇）");
			File	 file2 = new File("F:\\论文数据\\ECCV（2016至2020，3033份）");
			//File file = new File("F:\\论文数据\\ICCV（2001年至2019年，3196篇）");
			List<Paper> datalist = getECCV(file2);	        					
			/*for(int i = 0 ; i < datalist.size() ; i++) {
				  System.out.println(datalist.get(i).getUrl());
			}*/
			
			
			String sql="insert into paper(paperid,title,url,about,keyword) value(?,?,?,?,?)";
			DBUtils dbUtils = new DBUtils();
			dbUtils.init();
			Connection conn =dbUtils.getConn();
			PreparedStatement pstm = null;
			
			try {
				for(int i = 0 ; i < datalist.size() ; i++) {
					pstm=conn.prepareStatement(sql);
					pstm.setString(1, datalist.get(i).getId());
					pstm.setString(2, datalist.get(i).getTitle());
					pstm.setString(3, datalist.get(i).getUrl());
					pstm.setString(4, datalist.get(i).getAbout());
					pstm.setString(5, datalist.get(i).getKeywords());
					pstm.executeUpdate();
					System.out.println(" success "+i+datalist.get(i).getAbout());
				}							
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbUtils.releseAll(conn, pstm, null);
			}
		
			
	}

}
