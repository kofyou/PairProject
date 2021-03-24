package docrawler;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.PaperBean;

public class IccvCrawler {
	
	private static String url = "https://dblp.uni-trier.de/db/conf/iccv/index.html";
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.57";
	private static String meetingName = "iccv";
	
	private LinkedList<PaperBean> paperBeans = new LinkedList<PaperBean>();
	LinkedList<String> meetingUrls = new LinkedList<String>();
	
	private Connection getConnection() {
		Connection conn = Jsoup.connect(url);
		conn.userAgent(userAgent);
		conn.timeout(5000);
		conn.maxBodySize(0);
		return conn;
	}
	
	private void getMeetingUrls(){
		
		Connection conn = getConnection();
		
		try {
			Document doc = conn.get();
			Elements elements=doc.select("a.toc-link");
			for (Element element: elements) {
				meetingUrls.add(element.attr("href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void doCrawler() {
		//将结果存入数据库
	}
//	public static void main(String []args) {
//		LinkedList<String> urls = getMeetingUrls();
//		System.out.println(urls);
//	}
}
