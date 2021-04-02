package jiekou.demo.controller;

import jiekou.demo.entity.Keyword;
import jiekou.demo.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("keyword")
    public List<Keyword> getKeyword(){
        return keywordService.findTopten();
    }
}