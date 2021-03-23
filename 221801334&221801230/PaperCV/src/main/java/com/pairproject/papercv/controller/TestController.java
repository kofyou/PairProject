package com.pairproject.papercv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @date 2021/03/22
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
