package com.jee.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jee.dto.BlogQuery;
import com.jee.dto.SearchBlog;
import com.jee.dto.ShowBlog;
import com.jee.pojo.Blog;
import com.jee.pojo.User;
import com.jee.service.BlogService;
import com.jee.service.TagService;
import com.jee.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
    }
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<BlogQuery> allBlog = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/input")
    public String toAdd(Model model){
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        System.out.println(blog.getUser().getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message","新增成功");
        return "redirect:/admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog,Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        blogService.transformRecommend(searchBlog);
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum,3);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        model.addAttribute("message","查询成功");
        return "admin/blogs";
    }
    @GetMapping("/blogs/{id}/input")
    public String toUpdate(@PathVariable Long id, Model model){
        ShowBlog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-update";
    }
    @PostMapping("/blogs/update")
    public String editPost(ShowBlog showBlog,RedirectAttributes attributes){
        showBlog.setUpdateTime(new Date());
        blogService.updateBlog(showBlog);
        attributes.addFlashAttribute("message","修改成功");
        return "redirect:/admin/blogs";
    }

}
