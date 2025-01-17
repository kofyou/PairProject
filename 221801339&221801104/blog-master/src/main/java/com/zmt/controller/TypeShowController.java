package com.zmt.controller;

import com.zmt.po.Type;
import com.zmt.service.BlogService;
import com.zmt.service.TypeService;
import com.zmt.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeServiceImpl;


    @Autowired
    private BlogService blogServiceImpl;


    @GetMapping("/types/{id}")
    public String types(Model model, @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
            @PathVariable Long id, Pageable pageable) {

        List<Type> types = typeServiceImpl.listTypeTop(10000);
        if (id == -1){
            id = types.get(0).getId();
        }
        com.zmt.vo.BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogServiceImpl.ListBlog(pageable,blogQuery));
        model.addAttribute("avtiveTypeId",id);
        return "types";
    }

}
