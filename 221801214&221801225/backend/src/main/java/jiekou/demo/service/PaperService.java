package jiekou.demo.service;

import jiekou.demo.entity.Paper;
import jiekou.demo.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired //依赖
    private PaperMapper paperMapper;

    public List<Paper> findAll(){
        //业务逻辑
        return paperMapper.findAll();
    }

        public List<Paper> searchPage(int id){

        return paperMapper.searchPage(id);
    }

//    public List<Paper> searchPage_(String title1){
//
//        return paperMapper.searchPage_(title1);
//    }
}
