package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 21:54 2021/3/22
 */
@Controller
public class TestController {

    @GetMapping("/left")
    public String getLeft() {
        return "left";
    }

}
