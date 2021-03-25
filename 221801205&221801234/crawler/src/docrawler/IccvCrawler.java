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
	
	private static String iccvUrl = "https://dblp.uni-trier.de/db/conf/iccv/index.html";
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.57";
	private static String meetingName = "iccv";
	
	private LinkedList<PaperBean> paperBeans = new LinkedList<PaperBean>();
	private LinkedList<String> meetingUrls = new LinkedList<String>();
	private LinkedList<String> paperUrls = new LinkedList<String>();
	
	private Connection getConnection(String url) {
		Connection conn = Jsoup.connect(url);
		conn.userAgent(userAgent);
		conn.timeout(50000);
		conn.maxBodySize(0);
		return conn;
	}
	
	public void getMeetingUrls(){
		Connection conn = getConnection(iccvUrl);
		
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
	
	public void getPaperUrls() {
		for (String meetingUrl:meetingUrls) {
			Connection conn = getConnection(meetingUrl);
			try {
				Document doc = conn.get();
				Elements elements = doc.select("div#main ul.publ-list nav.publ ul li:first-child div.head a");
				for (Element element:elements) {
					String paperUrl = element.attr("href");
					if (paperUrl.contains("doi.org"))
						paperUrls.add(paperUrl);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getName(Connection conn) {
		String name = null;
		try {
			Document doc = conn.get();
			Element e = doc.selectFirst("h1.document-title span");
			name = e.text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
}
