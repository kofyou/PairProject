package com.yrc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@NoArgsConstructor
public class Question {
    private static int firstId;
    private int id;//问题的id
    private int id_ask;//提问人的id
    private int id_answer;//回答者的id
    private String context;
    private String answer;
    private String time; // 提问时间
    private int statue;//问题状态 1已回复 0未回复 2已删除


    public Question(int id,int id_ask, int id_answer,String context) {
        this.id=id;
        this.id_ask = id_ask;
        this.id_answer = id_answer;
        this.context = context;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(d);
        this.time=time;
        this.statue = 0;
    }
}
