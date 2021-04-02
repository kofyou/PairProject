package com.yrc.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.websocket.Session;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private Session session;
    private int id;
    private String name;
    //@Size(min = 6, max = 30, message = "密码长度应当在 6 ~ 30 个字符之间")
    private String password;
    //@Email(message = "错误的邮箱格式")
    private String email;
    private int statue;//状态 0:不接受提问  1:接受提问 默认为0
    private int email_statue; //邮箱激活状态，默认为0：为激活  1：激活
    private String filePath; //头像存储路径
    private int tip_off_statue;//封禁状态0:未被禁  1：被禁
    private int asks; //提问数
    private int answers;//回答数
    private int askeds;//被提问数

    public User(int id,String name, String password, String email) {
        this.id= id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.statue=0;
        this.email_statue=0;
        this.filePath = "/static/img/vanyang.jpg";
        this.tip_off_statue=0;
        this.asks=0;
        this.answers=0;
        this.askeds=0;
    }
    public User (String name,Session session)//站内消息临时用户
    {
        this.name=name;
        this.session=session;
    }

}
