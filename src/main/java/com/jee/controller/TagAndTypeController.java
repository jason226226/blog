package com.jee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jee.dto.FirstPageBlog;
import com.jee.pojo.Tag;
import com.jee.pojo.Type;
import com.jee.service.BlogService;
import com.jee.service.TagService;
import com.jee.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagAndTypeController {
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                      @PathVariable Long id, Model model){

        List<Tag> tags = tagService.getUseTag();
        if(id==-1){
            id=tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        List<FirstPageBlog> blogs  = blogService.getByTagId(id);
        PageHelper.startPage(pageNum,100);
        PageInfo<FirstPageBlog> PageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",PageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }

    @GetMapping("types/{id}")
    public String type(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                       @PathVariable Long id,Model model){
        List<Type> types = typeService.getUseType();
        if(id==-1){
            id=types.get(0).getId();
        }
        model.addAttribute("types",types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        PageHelper.startPage(pageNum,100);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
