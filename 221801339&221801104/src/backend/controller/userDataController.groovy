package com.yrp.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class userDataController {
    @GetMapping("/admin/data")
    public String about(){
        return "admin/data";
    }
}
