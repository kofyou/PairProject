package jiekou.demo.service;


import jiekou.demo.entity.Paperkey;
import jiekou.demo.mapper.PaperKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperkeyService {
    @Autowired
    private PaperKeyMapper paperkeyMapper;

    public List<Paperkey> findAll(){
        return paperkeyMapper.findAll();
    }
}
