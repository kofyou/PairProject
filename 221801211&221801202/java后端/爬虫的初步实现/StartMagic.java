package cn.sm.demo;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

class StartMagic
{
    public static void main(String[] args) {
        Spider.create(new MyProcesser())
                .addUrl("https://dblp.uni-trier.de/db/conf/eccv/eccv2020-1.html")
               // .addUrl("https://cnblogs.com/")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("e:/WebLib"))
                .addPipeline(new FilePipeline("e:/File"))
                .addPipeline(new MyPipeline())
                .run();
    }
}