package com.jee.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jee.pojo.Type;
import com.jee.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Type> allType = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }
    @GetMapping("types/input")
    public String toAdd(){
        return "admin/types-input";
    }
    @PostMapping("/types/add")
    public String Add(Type type, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName!=null){
            attributes.addFlashAttribute("message","不能添加重复的类型");
            return "redirect:/admin/types/input";
        }
        typeService.saveType(type);
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "admin/types-update";
    }
    @PostMapping("/types/update")
    public String editPost(Type type, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName!=null){
            attributes.addFlashAttribute("message","类型已存在");
            return "redirect:/admin/types";
        }
        typeService.updateType(type);
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        int result = typeService.deleteType(id);
        if (result == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }

}
