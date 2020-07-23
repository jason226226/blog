package com.jee.controller;

import com.jee.pojo.Comment;
import com.jee.pojo.User;
import com.jee.service.BlogService;
import com.jee.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.commends(blogId);
        model.addAttribute("comments",comments);
        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlogId();
        comment.setBlog(blogService.getDetailedBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
        }else{
            comment.setAvatar(avatar);
        }
        if(comment.getParentComment().getId()!=null){
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);

        return "redirect:/comments/" + blogId;

    }
}
