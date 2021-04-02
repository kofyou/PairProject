package com.pair.papercrawler.service;

import com.alibaba.fastjson.JSONObject;
import com.pair.papercrawler.models.Frequency;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<Frequency> gettop20();
}




