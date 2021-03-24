package com.example.demo.controller;

import com.example.demo.bean.PaperResponsBody;
import com.example.demo.bean.User;
import com.example.demo.service.serviceImpl.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController
{

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

    @GetMapping("/login")
    public String login(User user, HttpSession session, Model model)
    {
        System.out.println("开始登录");
        User user1=indexSerice.login(user);
        if (user1!=null)
        {
            user1.setPassword("");
            session.setAttribute("userName",user1.getName());
            model.addAttribute("userName",user1.getName());
            System.out.println(user1.getName());
        }
        else
        {
            session.setAttribute("userName","未登录");
        }
        return "index";
    }


}
