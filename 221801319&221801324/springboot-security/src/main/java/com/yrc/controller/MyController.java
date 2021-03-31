package com.yrc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MyController {  //一些页面的跳转

    @RequestMapping("/userLogon")
    public String toIndex (Model model){
        model.addAttribute("msg","Hello");
        return "userLogon";
    }

    @RequestMapping("/activate")
    public String toActivate (){
        return "activate";
    }

    @RequestMapping({"/","/index.html"})
    public String toIndex (){
        return "index";
    }

    @RequestMapping("/usermain")
    public String toMain (HttpSession session,Model model){model.addAttribute("filePath",session.getAttribute("filePath"));
        return "userMain";
    }

    @RequestMapping("/personalInfo")
    public String toPersonalInfo (HttpSession session,Model model){
        model.addAttribute("filePath",session.getAttribute("filePath"));return "personalInfo";
    }

    @RequestMapping("/updateInfo")
    public String toUpdateInfo (){
        return "updateInfo";
    }

    @RequestMapping("/updateName")
    public String toUpdateName (){
        return "updateName";
    }

    @RequestMapping("/updateEmail")
    public String updateEmail (){
        return "updateEmail";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword (){
        return "updatePassword";
    }

    @RequestMapping("/Safe")
    public String toSafe (){
        return "Safe";
    }

    @RequestMapping("/resetpwdEmail")
    public String toresetpwdEmail() {
        return "resetpwdEmail";
    }

    @RequestMapping("/resetpwd")
    public String toresetpwd() {
        return "resetpwd";
    }

    @RequestMapping("/ask_question")
    public String to_ask_question() {
        return "ask_question";
    }

    @RequestMapping("/answer_question")
    public String to_answer_question() {
        return "answer_question";
    }

    @RequestMapping(value = "/tip_off/{id_ask}") //举报
    public String tip_off (HttpSession session, Model model,
                           @PathVariable("id_ask") int id_ask){
        session.setAttribute("tip_off_id",id_ask);
        return "tip_off";
    }

    @RequestMapping(value = "/answer_question/{question_id}") //回答问题
    public String to_answer_question (HttpSession session, Model model,
                           @PathVariable("question_id") int question_id){
        session.setAttribute("question_id",question_id);
        return "answer_question";
    }

    @RequestMapping(value = "/admin")
    public String to_admin (){
        return "admin";
    }


    @RequestMapping(value = "/user_query")
    public String to_user_query (){
        return "user_query";
    }

    @RequestMapping (value = "/websocket")
            public String to_websocket()
    {
        return "websocket";
    }

    @RequestMapping (value = "/updateHead")
    public String to_update_head()
    {
        return "updateHead";
    }
}
