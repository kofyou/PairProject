package jiekou.demo.service;

import jiekou.demo.entity.Magazine;
import jiekou.demo.mapper.MagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {

    @Autowired
    MagazineMapper magazineMapper;

    public List<Magazine> findmagazine(){
        return magazineMapper.findmagazine();
    }
}
