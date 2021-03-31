package jiekou.demo.service;

import jiekou.demo.entity.Keyword;
import jiekou.demo.mapper.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordMapper keywordMapper;

    public List<Keyword> findTopten(){
        return keywordMapper.findTopten();
    }
}