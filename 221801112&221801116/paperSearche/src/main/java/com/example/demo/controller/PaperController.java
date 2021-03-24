package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Paper;
import com.example.demo.service.PaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: shuKai
 * @Description:
 * @Date: Create in 12:55 2021/3/23
 */

@RestController
@RequestMapping("PaperTable")
public class PaperController extends ApiController{

    @Resource
    private PaperService paperService;

    @GetMapping
    public R selectAll(Page<Paper> page,Paper paper) {
        return success(this.paperService.page(page, new QueryWrapper<>(paper)));
    }



}
