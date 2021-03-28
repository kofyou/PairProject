package cn.sm.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyProcesser implements PageProcessor {
    public void process(Page page) {
        System.out.println(page.getHtml().toString());               获得网站的H5
        page.addTargetRequests((page.getHtml().links().all()));     爬取全部内容
        page.addTargetRequests(page.getHtml().links().regex(httpswww.cnblogs.com[w-]+p[0-9]+.html).all());    bokeyuan
        page.putField(title,page.getHtml().xpath([@id=cb_post_title_url]span));
        page.addTargetRequests(page.getHtml().links().regex(httpslink.springer.comchapter[0-9-%.]+.html).all());


    }

    public Site getSite() {
        return Site.me().setSleepTime(100).setRetrySleepTime(3);
    }


}
