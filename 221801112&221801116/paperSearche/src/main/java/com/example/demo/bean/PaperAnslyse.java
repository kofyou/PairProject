package com.example.demo.bean;

/**
 * @Description: 论文数据分析结果类
 * @Author: 曹鑫
 * @Date: 2021/3/31
 */
public class PaperAnslyse {
    String paId;
    String dataStaticData;
    String paperAnslyseData;
    String dataWordsCloud;
    String time;
    String conference;

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getPaId() {
        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String getDataStaticData() {
        return dataStaticData;
    }

    public void setDataStaticData(String dataStaticData) {
        this.dataStaticData = dataStaticData;
    }

    public String getPaperAnslyseData() {
        return paperAnslyseData;
    }

    public void setPaperAnslyseData(String paperAnslyseData) {
        this.paperAnslyseData = paperAnslyseData;
    }

    public String getDataWordsCloud() {
        return dataWordsCloud;
    }

    public void setDataWordsCloud(String dataWordsCloud) {
        this.dataWordsCloud = dataWordsCloud;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
