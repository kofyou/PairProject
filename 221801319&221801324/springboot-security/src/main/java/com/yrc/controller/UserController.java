package com.yrc.controller;

import com.yrc.Util.CodeUtil;
import com.yrc.config.UserToken;
import com.yrc.mapper.BlackMapper;
import com.yrc.mapper.QuestionMapper;
import com.yrc.mapper.Tip_offMapper;
import com.yrc.mapper.UserMapper;
import com.yrc.pojo.Black;
import com.yrc.pojo.Question;
import com.yrc.pojo.User;
import com.yrc.service.MailService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@Validated
public class UserController {

    @Autowired
    private Tip_offMapper tip_offMapper;

    @Autowired
    private BlackMapper blackMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @ResponseBody
    @GetMapping("/queryUserList")//取出所有用户（测试）
    public List<User> queryUserList() {
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @RequestMapping(value = "/user/logon") //用户注册
    public String logon(@RequestParam("new_username") String new_username,
                        @RequestParam("new_password") String new_password,
                        @RequestParam("new_password1") String new_password1,
                        @RequestParam("email") String email,
                        Model model,
                        HttpSession session,
                        HttpServletRequest request) {
        List<User> userList = userMapper.queryUserList();
        int id = userList.size();
        for (User user : userList) {
            if (new_username.equals(user.getName())) {
                model.addAttribute("msg1", "此用户名已存在");
                return "userLogon";
            }
            if (email.equals(user.getEmail())) {
                model.addAttribute("msg3", "此邮箱已被注册");
                return "userLogon";
            }
        }
        if (!new_password.equals(new_password1)) {
            model.addAttribute("msg2", "两次输入密码不一致");
            return "userLogon";
        }
        boolean result = new_password.matches("[0-9]*");//正则判断密码是否为纯数字
        if (result == true) {
            System.out.println("该字符串是纯数字");
            model.addAttribute("msg4", "密码不可为纯数字");
            return "userLogon";
        }

        String reg = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";//正则检测邮箱格式
        if (!(email.matches(reg))) {
            System.out.println("邮箱输入格式错误");
            model.addAttribute("msg5", "邮箱输入格式错误");
            return "userLogon";
        }
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg6", "验证码错误");
            return "userLogon";
        } else {
            String md5Password = DigestUtils.md5DigestAsHex(new_password.getBytes());//密码MD5加密
            User user = new User(id, new_username, md5Password, email);
            userMapper.addUser(user);
            return "longonSccess";
        }
    }

    @RequestMapping(value = "/user/login")  //用户登录
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session,
                        HttpServletRequest request
    ) {

        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg", "验证码错误");
            return "index";
        }
        Subject subject = SecurityUtils.getSubject();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());//密码MD5加密
        //2.封装用户数据
        UserToken token = new UserToken(username, md5Password,"User");
        try {
            subject.login(token);//执行登录方法
            session.setAttribute("loginUser",username);
            queryUserByName(session);
            model.addAttribute("filePath",session.getAttribute("filePath"));
            return "userMain";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "index";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "index";
        }catch (DisabledAccountException e) //用户被封禁
        {
            return "tip_off_index";
        }
    }

//    @RequestMapping(value = "/email")
//    public void  email (){
//        userMapper.updateEmail_statue("yrc");
//    }


    @RequestMapping(value = "/email_active") //激活邮箱
    public String email_active (HttpSession session,
                                Model model,
                                HttpServletRequest request){
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg8", "验证码错误");
            return "activate";
        }
        userMapper.updateEmail_statue((String) session.getAttribute("loginUser"));
        queryUserByName(session);
        return "activateSuccess";
    }




    public User queryUserByName (HttpSession session){ //根据name session查询/更新用户信息
        User user =userMapper.queryUserByName((String)session.getAttribute("loginUser"));
        session.setAttribute("id",user.getId());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("email_statue",user.getEmail_statue());
        session.setAttribute("statue",user.getStatue());
        session.setAttribute("password",user.getPassword());
        session.setAttribute("filePath",user.getFilePath());
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/activate_email") //激活邮箱
    public String activate_email (HttpSession session)throws MessagingException {
        if ((int) session.getAttribute("email_statue")==1)
        {
            return "emailHaveActivate";
        }
        else {
            mailService.TemplateMail((String) session.getAttribute("email"));
            return "emailSuccess";
        }
    }

    @RequestMapping(value = "/activate_box")  //激活提van箱
    public String activate_box (HttpSession session) {
        if ((int) session.getAttribute("email_statue") == 1)
        {
            userMapper.updateBox_statue((String) session.getAttribute("loginUser"));
            queryUserByName(session);
            return "BoxActivateSuccess";
        } else {
            return "BoxFail";
        }
    }

    @RequestMapping(value = "/close_box") //关闭提van箱
    public String close_box (HttpSession session){
        userMapper.close_box((String) session.getAttribute("loginUser"));
        queryUserByName(session);
        return "close_box_success";
    }


    @RequestMapping(value = "/update_username") //更改用户名
    public String update_username (HttpSession session,
                                   @RequestParam("new_username") String new_username,
                                   HttpServletRequest request,
                                   Model model){
        List<User> userList = userMapper.queryUserList();
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg10", "验证码错误");
            return "updateName";
        }
        for (User user : userList) {
            if (user.getName().equals(new_username))
            {
                model.addAttribute("msg9","此用户名已存在");
                return "updateName";
            }
        }
        userMapper.updateName(new_username,(Integer) session.getAttribute("id"));
        queryUserById(session);
        return "updateNameSuccess";
    }

    public User queryUserById (HttpSession session){ //根据id session查询/更新用户信息
        User user =userMapper.queryUserById((int)session.getAttribute("id"));
        session.setAttribute("loginUser",user.getName());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("email_statue",user.getEmail_statue());
        session.setAttribute("statue",user.getStatue());
        session.setAttribute("password",user.getPassword());
        session.setAttribute("filePath",user.getFilePath());
        System.out.println(user);
        return user;
    }

    public User queryUserByEmail (HttpSession session){ //根据id session查询/更新用户信息
        User user =userMapper.queryUserByEmail((String)session.getAttribute("email"));
        session.setAttribute("id",user.getId());
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/resetpwd_email") //通过邮箱重置密码
    public String resetpwdEmail (@RequestParam("email") String email,
                                 HttpServletRequest request,
                                 Model model     ,
                                 HttpSession session           )throws MessagingException{
        List<User> userList = userMapper.queryUserList();
        session.setAttribute("email",email);
        queryUserByEmail(session);
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg17", "验证码错误");
            return "resetpwdEmail";
        }
        String reg = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";//正则检测邮箱格式
        if (!(email.matches(reg))) {
            System.out.println("邮箱输入格式错误");
            model.addAttribute("msg17", "邮箱输入格式错误");
            return "resetpwdEmail";
        }
        mailService.TemplateMail2((String) session.getAttribute("email"));
        return "emailSuccess";
    }

    @RequestMapping(value = "/reset_pwd")  //通过邮箱重置密码
    public String resetpwd (@RequestParam("new_password") String new_password,
                            @RequestParam("new_password1") String new_password1,
                            HttpServletRequest request,
                            Model model     ,
                            HttpSession session           ){
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg17", "验证码错误");
            return "resetpwd";
        }
        if (!new_password.equals(new_password1)) {
            model.addAttribute("msg17", "两次输入密码不一致");
            return "resetpwd";
        }
        boolean result = new_password.matches("[0-9]*");//正则判断密码是否为纯数字
        if (result == true) {
            System.out.println("该字符串是纯数字");
            model.addAttribute("msg17", "密码不可为纯数字");
            return "resetpwd";
        }
        String md5Password = DigestUtils.md5DigestAsHex(new_password.getBytes());//密码MD5加密
        userMapper.updatePassword(md5Password,(int)session.getAttribute("id"));
        return "resetpwdS";
    }





    @RequestMapping(value = "/update_password")  //更改密码
    public String updatePassword (HttpSession session,
                                   @RequestParam("new_password") String new_password,
                                   @RequestParam("new_password1") String new_password1,
                                   @RequestParam("old_password") String old_password,
                                   HttpServletRequest request,
                                   Model model){
        String md5_OldPassword = DigestUtils.md5DigestAsHex(old_password.getBytes());//密码MD5加密
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg11", "验证码错误");
            return "updatePassword";
        }
        if (!md5_OldPassword.equals((String)session.getAttribute("password")))
        {
            model.addAttribute("msg12", "旧密码错误");
            return "updatePassword";
        }
        if (!new_password.equals(new_password1)) {
            model.addAttribute("msg13", "两次输入密码不一致");
            return "updatePassword";
        }
        boolean result = new_password.matches("[0-9]*");//正则判断密码是否为纯数字
        if (result == true) {
            System.out.println("该字符串是纯数字");
            model.addAttribute("msg14", "密码不可为纯数字");
            return "updatePassword";
        }
        String md5_NewPassword = DigestUtils.md5DigestAsHex(new_password.getBytes());
        userMapper.updatePassword(md5_NewPassword,(int)session.getAttribute("id"));
        queryUserById(session);
        return "updatePasswordSuccess";
    }

    @RequestMapping(value = "/update_email") //更改邮箱
    public String updateEmail (HttpSession session,
                               @RequestParam("new_email") String new_email,
                               HttpServletRequest request,
                               Model model){
        if (!CodeUtil.checkVerifyCode(request)) {
            System.out.println("验证码错误");
            model.addAttribute("msg15", "验证码错误");
            return "updateEmail";
        }
        String reg = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";//正则检测邮箱格式
        if (!(new_email.matches(reg))) {
            System.out.println("邮箱输入格式错误");
            model.addAttribute("msg16", "邮箱输入格式错误");
            return "updateEmail";
        }
        userMapper.updateEmail(new_email,(int) session.getAttribute("id"));
        queryUserById(session);
        return "updateEmailSuccess";
    }

    @RequestMapping(value = "/question_list") //用户问题列表
    public String User_questions (HttpSession session,Model model){
        List<Question> questionList = questionMapper.queryQuestionByAnswerId((int) session.getAttribute("id"));
        List<Black> blackList = blackMapper.queryBlack();//获取黑名单
        for (int i = questionList.size() - 1; i >= 0; i--) {
            for (int j = blackList.size() - 1; j >= 0; j--){
                Question question = questionList.get(i);
                Black black = blackList.get(j);
                if (question.getId_answer()==black.getBlack()&&question.getId_ask()==black.getBlacked()){
                    questionList.remove(question);  //不显示拉黑者提问
                }
            }
        }
        model.addAttribute("questionlist",questionList);
        return "question_list";
    }


    @RequestMapping(value = "/tip_off") //举报某个提问用户
    public String tip_off (HttpSession session,Model model,
                           @RequestParam("reason") String reason
                           ){
        tip_offMapper.addTip_off((int)session.getAttribute("tip_off_id"),reason);//举报成功
        return "tip_off_S";
    }


    @RequestMapping(value = "/black/{id_ask}") //拉黑提问用户
    public String tip_off (HttpSession session, Model model,
                           @PathVariable("id_ask") int id_ask){
        List<Black> blackList = blackMapper.queryBlack();
        for (Black black : blackList) {
            if ((int)session.getAttribute("id")==black.getBlack()&&id_ask==black.getBlacked());
            return "black_F";
        }
        blackMapper.addBlack((int)session.getAttribute("id"),id_ask);
        return "black_S";
    }


    @RequestMapping(value = "/askQuestion") //问问题
    public String ask_question (@RequestParam ("name") String name,
                                @RequestParam ("context") String context,
                                HttpSession session,
                                Model model){
        List<Question> questionList= questionMapper.queryQuestionList();
        int id=questionList.size();
        User user = userMapper.queryUserByName(name);
        List<User> userList = userMapper.queryUserList();
        if (user==null)
        {
            model.addAttribute("msg","该用户不存在");
            return "ask_question";
        }

        if ((int)session.getAttribute("statue")==0)
        {
            model.addAttribute("msg","你未开启提van箱或未激活邮箱");
            return "ask_question";
        }
        if (user.getStatue()==1&&user.getTip_off_statue()==0) //被问用户是否被封或未接受提问
        {
            userMapper.add_asked(user.getId());
            userMapper.add_ask((int)session.getAttribute("id"));
            Question question = new Question(id,(int)session.getAttribute("id"),user.getId(),context);
            questionMapper.addQuestion(question);
            return "ask_question_S";
        }
        return "ask_question_F";
    }

    @RequestMapping(value = "answerQuestion") //回答问题
    public String answerQuestion (@RequestParam ("answer") String answer,
                                  HttpSession session,
                                  Model model){

        userMapper.add_answer((int)session.getAttribute("id"));
        questionMapper.answerQuestion((int)session.getAttribute("question_id"),answer);
        return "answer_question_S";
    }


    @RequestMapping(value = "/my_question_list") //我问的问题 列表
    public String to_my_question_list (HttpSession session,Model model){
        List<Question> questionList = questionMapper.queryQuestionByAskId((int) session.getAttribute("id"));
        for (int i = questionList.size() - 1; i >= 0; i--) {
            Question question = questionList.get(i);
            if (question.getStatue()==2)
            {
                questionList.remove(question);
            }
        }
        model.addAttribute("my_question_list",questionList);
        return "my_question_list";
    }

    @RequestMapping(value = "/delete/{id}") //删除我的问题
    public String delete_question (HttpSession session, Model model,
                           @PathVariable("id") int id){
        userMapper.delete_ask((int)session.getAttribute("id"));
        questionMapper.deleteQuestion(id);
        return "my_question_list";
    }

    /**
     *
     * 客户端页面
     * @return
     */
    @RequestMapping(value = "/user/socket") //用户消息
    public String index(HttpSession session,Model model) {
        model.addAttribute("filePath",session.getAttribute("filePath"));

        return "indexSocket";
    }

    @ResponseBody
    @RequestMapping(value = "/update_head") //用户上传头像,它会弹出请求窗口异常，但是上传是成功的，我目前没搞懂
    public JSONObject update_head (HttpServletRequest request,HttpSession session,Model model,
                               @RequestParam("file") MultipartFile file) throws Exception {
        JSONObject json = new JSONObject();
        String filePath = "/root/imgs";//上传到这个
        File file1 = new File(filePath);
        System.out.println(filePath);
        String fileName=file.getOriginalFilename();
        System.out.println(fileName);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String finalFilePath = filePath + "/"+file.getOriginalFilename().trim();
        File desFile = new File(finalFilePath);
        if (desFile.exists()) {
            desFile.delete();
        }
        try {
            file.transferTo(desFile);
            json.put("code", 0);
            System.out.println("成功");
            String File = "/img/"+fileName;
            userMapper.update_head((int)session.getAttribute("id"),File);
            queryUserById(session);
            model.addAttribute("filePath",File);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            json.put("code", 1);
        }
        return json;
    }
}
