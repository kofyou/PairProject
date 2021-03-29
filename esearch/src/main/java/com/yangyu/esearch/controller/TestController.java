package com.yangyu.esearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.controller.PaperController
 * @Description : TestController类
 * @date : 2021-03-23 22:28:28
 * Copyright  2021 user. All rights reserved.
 */

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public String hello()
    {
        return "hello,world";
    }
}
