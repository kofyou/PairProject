package com.yrc.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class paper {

    private String title;
    private String p_abstract;
    private String typeandyear;
    private String keywords;
    private String releasetime;
    private String link;
    private int id;


}
