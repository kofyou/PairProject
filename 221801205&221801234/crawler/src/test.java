package com.company;



import com.company.message.ParseMessage;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s="\"111";
		String encode = URLDecoder.decode(s, "utf-8");
		String encode1 = URLEncoder.encode(s, "utf-8");
		System.out.println(encode1);
		/*ParseMessage p = new ParseMessage();
		
		File file = new File("C:\\Users\\25820\\Desktop\\论文数据\\论文数据");
		File[] paperFiles = file.listFiles();
		for (File f:paperFiles) {
			if (f.getName().equals("ECCV")) {
				File[] papers = f.listFiles();
				for (File paper:papers) {
					try {
						FileReader reader = new FileReader(paper);
						int length = (int)paper.length();
						char[] content = new char[length];
						try {
							reader.read(content);
							p.getMessageECCV(String.valueOf(content));
						} catch (IOException e) {
							e.printStackTrace();
						}finally {
							try {
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else if (f.getName().equals("ICCV")) {
				File[] papers = f.listFiles();
				for (File paper:papers) {
					try {
						FileReader reader = new FileReader(paper);
						int length = (int)paper.length();
						char[] content = new char[length];
						try {
							reader.read(content);
							p.getMessageICCVandCVPR(String.valueOf(content),"ICCV");
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else if (f.getName().equals("CVPR")) {
				File[] papers = f.listFiles();
				//System.out.println(f.getName());
				for (File paper:papers) {
					try {
						FileReader reader = new FileReader(paper);
						int length = (int)paper.length();
						//System.out.println(length);
						char[] content = new char[length];
						try {
							reader.read(content);
							//System.out.println(String.valueOf(content));
							p.getMessageICCVandCVPR(String.valueOf(content),"CVPR");
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}*/
	}

}
