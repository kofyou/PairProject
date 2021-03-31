package jiekou.demo.controller;

import jiekou.demo.entity.Paperkey;
import jiekou.demo.service.PaperkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaperKeyController {

    @Autowired
    private PaperkeyService paperkeyService;

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/paperkey")
    public List<Paperkey> getPaperkey() {
        return paperkeyService.findAll();
    }
}
