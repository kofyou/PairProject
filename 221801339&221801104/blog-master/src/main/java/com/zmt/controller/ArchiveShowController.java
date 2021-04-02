package com.zmt.controller;

import com.zmt.po.Blog;
import com.zmt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;


@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogServiceImpl;


    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogServiceImpl.archiveBlog());
        model.addAttribute("blogCount", blogServiceImpl.countBlog());
        return "archives";
    }
}
