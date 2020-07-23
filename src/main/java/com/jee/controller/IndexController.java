package com.jee.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jee.dto.DetailedBlog;
import com.jee.dto.FirstPageBlog;
import com.jee.dto.RecommendBlog;
import com.jee.dto.ShowBlog;
import com.jee.pojo.Comment;
import com.jee.pojo.Tag;
import com.jee.pojo.Type;
import com.jee.service.BlogService;
import com.jee.service.CommentService;
import com.jee.service.TagService;
import com.jee.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.soap.Detail;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<FirstPageBlog> firstPageBlogs = blogService.getAllFirstPageBlog();
        List<Type> allType = typeService.getUseType();
        List<Tag> allTag = tagService.getUseTag();
        List<RecommendBlog> recommendBlogs = blogService.getRecommendBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",allTag);
        model.addAttribute("types",allType);
        model.addAttribute("recommendedBlogs", recommendBlogs);
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam String query){
        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageBlogPageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo",pageBlogPageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        DetailedBlog blog = blogService.getDetailedBlog(id);
        int views = blog.getViews() + 1;
        blog.setViews(views);
        ShowBlog showBlog = blogService.getBlogById(id);
        showBlog.setViews(views);
        blogService.updateBlog(showBlog);
        List<Comment> comments = commentService.commends(id);
        model.addAttribute("comments",comments);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
