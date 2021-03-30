package com.example.demo.controller;

import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperAnslyse;
import com.example.demo.bean.PaperResponsBody;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.serviceImpl.IndexServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController
{
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IndexServiceImpl indexSerice;

    @GetMapping("/t")
    public String welcome()
    {
        return "index";
    }

    @GetMapping("/t1")
    public String test()
    {
        return "test";
    }

    @RequestMapping(value = {"/", "/login"})
    public String login(User user, HttpSession session, Model model)
    {
        logger.debug("开始登录");
        User user1 = null;
        if (user.getName() != null)
            user1 = indexSerice.login(user);
        if (user1 != null)
        {
            user1.setPassword("");
            session.setAttribute("userName", user1.getName());
            model.addAttribute("userName", user1.getName());
            System.out.println(user1.getName());
            return "index";
        } else
        {
            session.setAttribute("userName", "请登录");
            model.addAttribute("userName", "请登录");
            System.out.println("请登录");
        }
        return "lastlogin";
    }


    @RequestMapping("/lastLogin")
    public String lastLogin(User user, HttpSession session, Model model)
    {
        return "lastlogin";
    }

    //本次commit
    @GetMapping("static")
    public String sta(HttpSession session, Model model)
    {
        logger.debug("获取统计结果");
        return "static";
    }

    @RequestMapping("/getPaper")
    @ResponseBody
    public Paper getPaper(User user, HttpSession session, Model model)
    {
        logger.debug("开始获取论文");
        List<User> list = null;
        List<Paper> paperList = indexSerice.getPaper();
        if (paperList == null)
            return null;
        return paperList.get(0);
    }

    @RequestMapping("/getAllPaper")
    @ResponseBody
    public PaperResponsBody getAllPaper()
    {
        PaperResponsBody paperResponsBody = new PaperResponsBody();
        paperResponsBody.setCode("0");
        paperResponsBody.setMsg("成功");
        paperResponsBody.setCount(1000);
        paperResponsBody.setData(indexSerice.getAllPaper());
        return paperResponsBody;
    }


    @RequestMapping("/topKeyWordsData")
    @ResponseBody
    public String getTopKeyWordsDataByConference(PaperAnslyse paperAnslyse)
    {
        return indexSerice.getPaperAnslyse(paperAnslyse).getPaperAnslyseData();
    }

    @RequestMapping("/topKeyWords")
    @ResponseBody
    public String getTopKeyWordsByConference(PaperAnslyse paperAnslyse)
    {
        return EmojiParser.parseToUnicode(indexSerice.getPaperAnslyse(paperAnslyse).getDataStaticData());
    }

    @RequestMapping("/getKeyWordCloud")
    public String testWord()
    {
        return "testWordCloud";
    }

    @RequestMapping("/topKeyWordCloud")
    @ResponseBody
    public String getTopKeyWordCloud()
    {
        PaperAnslyse paperAnslyse = new PaperAnslyse();
        paperAnslyse.setConference("CVPR");
        return indexSerice.getPaperAnslyse(paperAnslyse).getDataWordsCloud();
    }

    @RequestMapping("/savePaper")
    public boolean savePaper() throws JsonProcessingException
    {
        return indexSerice.saveAlalysePaperToGetTopKey();
    }

    @RequestMapping("/register")
    public String register(User user)
    {
        int ans=indexSerice.register(user);
        return "lastlogin";
    }

}
