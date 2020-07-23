package com.jee.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jee.pojo.Tag;
import com.jee.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }
    @GetMapping("/tags/input")
    public String toAdd(){
        return "admin/tags-input";
    }

    @PostMapping("/tags/add")
    public String add(Tag tag, RedirectAttributes attributes){
        Tag tag1 = tagService.getByName(tag.getName());
        if(tag1!=null){
            attributes.addFlashAttribute("message","标签已存在");
            return "redirect:/admin/tags/input";
        }
        attributes.addFlashAttribute("message","添加标签成功");
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getById(id));
        return "admin/tags-update";
    }
    @PostMapping("/tags/update")
    public String editPost(Tag tag,RedirectAttributes attributes) {
        Tag tag1 = tagService.getByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("message", "标签已存在");
            Long id = tag1.getId();
            return String.format("redirect:/admin/tags/%d/input", id);
        }
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        int result=tagService.deleteTag(id);
        if(result==1){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/tags";
    }
}
