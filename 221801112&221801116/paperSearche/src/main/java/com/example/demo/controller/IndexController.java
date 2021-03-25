package com.example.demo.controller;

import com.example.demo.bean.Paper;
import com.example.demo.bean.PaperResponsBody;
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

    @RequestMapping("/getPaper")
    @ResponseBody
    public Paper getPaper(User user, HttpSession session, Model model)
    {
        logger.debug("开始获取论文");
        List<User> list=null;
        if (indexSerice.getPaper()==null)
            return null;
        return indexSerice.getPaper().get(0);
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
}
