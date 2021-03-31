package com.pair.papercrawler.dao;

import com.alibaba.fastjson.JSONObject;
import com.pair.papercrawler.models.Frequency;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface FrequencyMapper {
    List<Frequency> getTop20();

}
