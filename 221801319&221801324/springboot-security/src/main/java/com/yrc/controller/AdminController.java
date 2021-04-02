package com.yrc.controller;

import com.yrc.Util.CodeUtil;
import com.yrc.config.UserToken;
import com.yrc.mapper.AdminMapper;
import com.yrc.mapper.QuestionMapper;
import com.yrc.mapper.Tip_offMapper;
import com.yrc.mapper.UserMapper;
import com.yrc.pojo.Admin;
import com.yrc.pojo.Question;
import com.yrc.pojo.Tip_off;
import com.yrc.pojo.User;
import com.yrc.service.MailService;
import com.yrc.service.SocketServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private SocketServer socketServer;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private Tip_offMapper tip_offMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;


//    @GetMapping("/queryAdminList")
//    public List<Admin> queryUserList (){
//        List<Admin> adminList = adminMapper.queryAdminList();
//        for (Admin admin : adminList) {
//            System.out.println(admin);
//        }
//        return adminList;
//    }

    @RequestMapping(value = "/admin/login") //管理员登录
    public String admin_login (@RequestParam ("admin") String admin,
                               @RequestParam ("password") String password,
                               Model model,
                               HttpServletRequest request) {
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg", "验证码错误");
            return "admin";
        }
        Subject subject = SecurityUtils.getSubject();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());//密码MD5加密
        UserToken token = new UserToken(admin, md5Password,"Admin");
        try {
            subject.login(token);//执行登录方法
            return "adminMain";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "admin";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "admin";
        }
    }




    @RequestMapping(value = "/tip_off_center")//举报中心
    public String to_tip_off_center (Model model){
        List<Tip_off> tip_offs = tip_offMapper.queryTipList();
        model.addAttribute("tip_off_list",tip_offs);
        return "tip_off_center";
    }

    @RequestMapping(value = "/get_tip_off/{id_ask}") //封禁用户
    public String get_tip_off (HttpSession session, Model model,
                           @PathVariable("id_ask") int id_ask){

        userMapper.tip_off(id_ask);
        return "tip_off_center";
    }

    @RequestMapping(value = "/remove_tip_off/{id_ask}") //解放用户
    public String remove_tip_off (HttpSession session, Model model,
                           @PathVariable("id_ask") int id_ask){


        userMapper.remove_tip_off(id_ask);
        return "tip_off_center";
    }

    @RequestMapping(value = "/admin/queryUser")  //管理员查询某个用户信息
    public String queryUser (@RequestParam ("name") String name,
                              Model model){  //管理员查询用户
        int tag=0;
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            if (name.equals(user.getName()))
                tag=1;
        }
        if (tag==0)
        {
            model.addAttribute("msg","用户名不存在");
            return "user_query";
        }
        User user = userMapper.queryUserByName(name);
        model.addAttribute("user",user);
        return "user_query_info";
    }


    @RequestMapping(value = "/admin/user_query_info/questions/{id}")  //管理员查看某个用户的问题
    public String user_query_info_questions (@PathVariable("id") int id,
                                             Model model){
        List<Question> questionList = questionMapper.queryQuestionByAskId(id);
        model.addAttribute("questionList",questionList);
        for (Question question : questionList) {
            System.out.println(question);
        }
        return "user_query_info_questions";
    }

    @RequestMapping(value = "/admin/user_query_info/answers/{id}")  //管理员查看某个用户的回答
    public String user_query_info_answers (@PathVariable("id") int id,
                                             Model model){
        List<Question> questionList = questionMapper.queryQuestionByAnswerId(id);
        model.addAttribute("questionList",questionList);
        return "user_query_info_answers";
    }

    @RequestMapping(value = "/admin/user_query_info/askeds/{id}")  //管理员查看某个用户的被提问的问题
    public String user_query_info_askeds (@PathVariable("id") int id,
                                           Model model){
        List<Question> questionList = questionMapper.queryQuestionByAnswerId(id);
        model.addAttribute("questionList",questionList);
        return "user_query_info_askeds";
    }


    @RequestMapping(value = "/all_users") //用户总览
    public String all_users (Model model){
        List<User> userList = userMapper.queryUserList();
        model.addAttribute("userList",userList);
        return "all_users";
    }

    /**
     *
     * 服务端页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/socket") //管理员发送消息给用户
    public String admin(Model model) {
        int num = socketServer.getOnlineNum();
        List<String> list = socketServer.getOnlineUsers();

        model.addAttribute("num",num);
        model.addAttribute("users",list);
        return "adminSocket";
    }
}
