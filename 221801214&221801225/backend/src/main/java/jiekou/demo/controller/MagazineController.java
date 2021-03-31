package jiekou.demo.controller;

import jiekou.demo.entity.Magazine;
import jiekou.demo.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class MagazineController {

    @Autowired
    private MagazineService magazineService;

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/magazine")
    public List<Magazine> getMagezineAll(){
        return magazineService.findmagazine();
    }
}
