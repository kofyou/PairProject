import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import message.ParseMessage;

public class test {

	public static void main(String[] args) {
		ParseMessage p = new ParseMessage();
		
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
		}
	}

}
