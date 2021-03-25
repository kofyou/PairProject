package com.example.demo.controller;

import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperResponsBody;
import com.example.demo.bean.StaticData;
import com.example.demo.bean.User;
import com.example.demo.service.serviceImpl.IndexServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = {"/","/login"})
    public String login(User user, HttpSession session, Model model)
    {
        logger.debug("开始登录");
        User user1=null;
        if (user.getName()!=null)
            user1=indexSerice.login(user);
        if (user1!=null)
        {
            user1.setPassword("");
            session.setAttribute("userName",user1.getName());
            model.addAttribute("userName",user1.getName());
            System.out.println(user1.getName());
        }
        else
        {
            session.setAttribute("userName","请登录");
            model.addAttribute("userName","请登录");
            System.out.println("请登录");
        }
        return "index";
    }

    //本次commit 不提交
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
        List<User> list=null;
        List<Paper> paperList=indexSerice.getPaper();
        if (paperList==null)
            return null;
        return paperList.get(0);
    }

    @RequestMapping("/getAllPaper")
    @ResponseBody
    public PaperResponsBody getAllPaper()
    {
        PaperResponsBody paperResponsBody=new PaperResponsBody();
        paperResponsBody.setCode("0");
        paperResponsBody.setMsg("成功");
        paperResponsBody.setCount(1000);
        paperResponsBody.setData(indexSerice.getAllPaper());
        return paperResponsBody;
    }


    @RequestMapping("/topKeyWordsData")
    @ResponseBody
    public List<List<String>> getTopKeyWordsDataByConference(Paper paper)
    {
        List<Map.Entry<String, Integer>> lists=indexSerice.alalysePaperToGetTopKeyWords(paper);
        List<List<String>> listList=new ArrayList<>();
        List<String> listStr=new ArrayList<>();

        listStr.add("Income");
        listStr.add("Life Expectancy");
        listStr.add("Population");
        listStr.add("Country");
        listStr.add("Year");
        listList.add(listStr);
        System.out.println("获得的数据数量"+lists.size());
        for (int i=0;i<lists.size();i++)
        {
            List<String> tmp=new ArrayList<>();
            tmp.add(0,String.valueOf(lists.get(i).getValue()));
            tmp.add(1,"");
            tmp.add(2,"");
            String[] values=lists.get(i).getKey().split("&&&&&");
            tmp.add(3,values[0]);
            tmp.add(4,values[1]);
            listList.add(tmp);
        }

        return listList;
    }

    @RequestMapping("/topKeyWords")
    @ResponseBody
    public List<StaticData> getTopKeyWordsByConference(Paper paper)
    {
        List<Map.Entry<String, Integer>> lists=indexSerice.alalysePaperToGetTopKeyWords(paper);
        List<StaticData> listStr=new ArrayList<>();
        StaticData staticData=new StaticData();


        for (int i=0;i<lists.size();i++)
        {
            staticData=new StaticData();
            String[] values=lists.get(i).getKey().split("&&&&&");
            staticData.setName(values[0]);
            if (i%10==0)
                staticData.setEmoji("\uD83C\uDF12");
            else if (i%10==1)
                staticData.setEmoji("\uD83C\uDF13");
            else if (i%10==2)
                staticData.setEmoji("\uD83C\uDF14");
            else if (i%10==3)
                staticData.setEmoji("\uD83C\uDF15");
            else if (i%10==4)
                staticData.setEmoji("\uD83C\uDF17");
            else if (i%10==5)
                staticData.setEmoji("\uD83C\uDF18");
            else if (i%10==6)
                staticData.setEmoji("\uD83C\uDF19");
            else if (i%10==7)
                staticData.setEmoji("\uD83C\uDF1B");
            else if (i%10==8)
                staticData.setEmoji("\uD83C\uDF1C");
            else if (i%10==9)
                staticData.setEmoji("☀️");
            listStr.add(staticData);
        }

        return listStr;
    }
}
