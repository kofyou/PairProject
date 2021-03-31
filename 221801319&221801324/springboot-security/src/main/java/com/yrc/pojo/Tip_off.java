package com.yrc.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tip_off { //举报表
    private int id;//被举报人id
    private String reason;//举报理由
}
