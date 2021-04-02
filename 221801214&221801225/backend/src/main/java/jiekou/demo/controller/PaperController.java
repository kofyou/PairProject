package jiekou.demo.controller;

import jiekou.demo.entity.Paper;
import jiekou.demo.entity.PaperKeyword;
import jiekou.demo.entity.Paperkey;
import jiekou.demo.service.PaperService;
import jiekou.demo.service.PaperkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;
    @Autowired
    private PaperkeyService paperkeyService;

    @RequestMapping("/paper")
    public List<Paper> getPaper(){
        return paperService.findAll();
    }



    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/searchpage/{k}/{search}")
    public List<Paper> searchPage(@PathVariable("k") String k,@PathVariable("search") String search){
        int choose = Integer.parseInt(k);
//        List<Paper> papers=paperService.searchPage(Integer.parseInt(search));
        List<Paper> papers=new ArrayList<>();
        if (choose==1){
            papers=paperService.searchPage(Integer.parseInt(search));
            List<Paperkey> paperkeys=paperkeyService.findAll();
        }
//        else if (choose==2){
//            papers=paperService.searchPage_(search);
//        }
        return papers;
    }



    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/changepage")
    public List<PaperKeyword> getPaging(){
        List<Paperkey> paperkeys=paperkeyService.findAll();
        List<Paper> papers=paperService.findAll();
        List<PaperKeyword> paperKeywords=new ArrayList<>();
        Paper paper=new Paper();
        int q=0;
        for(int i=0;i<papers.size(); i++){
            List<String> s=new ArrayList<>();
            paper=papers.get(i);
            while(paper.getPaper_id()==paperkeys.get(q).getPaper_id()){
                s.add(paperkeys.get(q).getWord());
                q++;
            }
            PaperKeyword paperKeyword=new PaperKeyword(paper.getPaper_id(),paper.getTitle(),paper.getLink(),paper.getPublication_year(),paper.getMagazine(),paper.getAbstracted(),s);
            paperKeywords.add(paperKeyword);
        }
        return paperKeywords;
    }
}
