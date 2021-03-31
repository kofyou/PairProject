package com.pair.papercrawler.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.cfg.PropertiesConfigSource;
import com.pair.papercrawler.dao.FrequencyMapper;
import com.pair.papercrawler.models.Frequency;
import com.pair.papercrawler.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    FrequencyMapper frequencyMapper;

    @Override
    public List<Frequency> gettop20(){
        List<Frequency> map = frequencyMapper.getTop20();
        return frequencyMapper.getTop20();
    }

}
