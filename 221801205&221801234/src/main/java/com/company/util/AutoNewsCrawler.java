package com.company.util;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.alibaba.fastjson.JSONObject;
import com.company.bean.Paper;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

public class AutoNewsCrawler extends BreadthCrawler {
    private int id=0;
    String s;
    int year;
    int cnt;
    String meeting;
    String seed1;
    String seed2;
    /**
    * http://www.informatik.uni-trier.de/~ley/db/conf/iccv/index.html
    * http://www.informatik.uni-trier.de/~ley/db/conf/cvpr/index.html
    * http://www.informatik.uni-trier.de/~ley/db/conf/eccv/index.html
    * */
    private ArrayList<Paper> papers;
    public AutoNewsCrawler(String crawlPath, boolean autoParse,int year,String meeting,int cnt) {
        super(crawlPath, autoParse);
        this.year=year;
        this.meeting="ICCV";
        this.cnt=cnt;
        this.seed2="https://dblp.uni-trier.de/db/conf/cvpr/cvpr"+year+".html";
        this.seed1="https://dblp.uni-trier.de/db/conf/iccv/iccv"+year+".html";
        papers = new ArrayList<>();
        if("ICCV".equals(meeting))
            this.addSeed(seed1);
        else this.addSeed(seed2);

        //this.addSeed("https://ieeexplore.ieee.org/document/9156584");
        this.addRegex("https://doi.org/[0-9]*.[0-9]*/"+meeting+".[0-9]*.[0-9]*");
        getConf().setConnectTimeout(3000);
        setThreads(50);
        getConf().setTopN(100);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();
        System.out.println(url);
        if (page.matchUrl("https://doi.org/[0-9]*.[0-9]*/" + meeting + ".[0-9]*.[0-9]*")) {
            try (BufferedReader reader = new BufferedReader(new StringReader(page.html()))) {
                while ((s = reader.readLine()) != null) {
                    s = s.trim();
                    //System.out.println(s);
                    if (s.length() != 0 && s.contains("xplGlobal.document.metadata")) {
                        papers.add(new Paper(s, year, meeting, url));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            id++;
            if (id > cnt) {
                this.stop();
            }
        }
    }

    public static ArrayList<Paper> run(int year,String meeting,int cnt) throws Exception{
        AutoNewsCrawler crawler = new AutoNewsCrawler("crawl", true,year,meeting,cnt);
        crawler.start(3);
        return crawler.papers;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(JSONObject.toJSONString(run(2015, "ICCV", 10)));
    }
}