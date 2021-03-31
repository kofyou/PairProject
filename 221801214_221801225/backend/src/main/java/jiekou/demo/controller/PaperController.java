package jiekou.demo.controller;

import jiekou.demo.entity.Paper;
import jiekou.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

    @RequestMapping("/paper")
    public List<Paper> getPaper(){
        return paperService.findAll();
    }



    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/accessLoginN/{k}")
    public List<Paper> getPaging(@PathVariable("k") String k){

       int n=Integer.parseInt(k);

        List<Paper> papers=paperService.findAll();
        List<Paper> papers1= new ArrayList<>();

        Paper paper=new Paper();

        for(int i=(n-1)*5,j=0;j<5; i++,j++){
            paper=papers.get(i);
            papers1.add(paper);
        }
        return papers1;
    }


}
